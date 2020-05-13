package ru.nsu.ccfit.radeev.commonclient.database.framework.core.visual;

import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.radeev.commonclient.database.framework.core.logic.TableLogic;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.*;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.ValidationResult;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.Validator;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros.Combobox;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros.FormattedTextField;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros.TextFieldEditor;
import ru.nsu.ccfit.radeev.commonclient.view.utils.LookupEntry;

import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static javax.swing.SwingUtilities.isRightMouseButton;

public class TableVisual extends JPanel {
    private static final int PANELS_NUMBER = 2;

    private static final SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final TableLogic tableModel;
    private final Table table;
    private JTable tableView;

    public TableVisual(Table table) throws SQLException {
        assert (table != null);
        this.table = table;
        this.tableModel = new TableLogic(table.getTableInfo());
        buildInterface();
    }

    private void buildInterface() {
        setLayout(new MigLayout());
        createTable();
        addTable();
        JPanel buttonPanel = createTableButtons();
        add(buttonPanel, "alignx left, wrap");
        JPanel addRecordPanel = createAddRecordPanel();
        add(addRecordPanel, "width 100%");
    }

    private JPanel createAddRecordPanel() {
        final JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("Новая запись"));

        mainPanel.setLayout(new MigLayout());
        final JPanel addRecordPanel = new JPanel();
        addRecordPanel.setBorder(BorderFactory.createTitledBorder("Поля новой записи"));
        addRecordPanel.setLayout(new MigLayout());

        final JPanel buttonPanel = new JPanel();

        mainPanel.add(addRecordPanel, "width 100%, wrap");
        mainPanel.add(buttonPanel, "width 100%, wrap");

        final TableInfo tableInfo = table.getTableInfo();
        final TableValidators validators = table.getValidators();

        final Map<Integer, String> columnsNameMap = new HashMap<>();
        final Map<Integer, Editor> editorList = new HashMap<>();
        int columnCount = tableInfo.getTableColumns().size();
        for (int i = 0; i < columnCount; i++) {
            int modelColumn = i;
            String labelName = tableInfo.getTableColumns().get(modelColumn);
            if(!tableInfo.getNewRecordColumns().contains(labelName)){
                continue;
            }
            columnsNameMap.put(modelColumn, labelName);

            JLabel label = new JLabel(labelName);
            if (tableInfo.isRequired(modelColumn)) {
                label.setForeground(Color.RED);
            }
            Editor editor = buildEditor(modelColumn);
            assert (null != editor);
            editorList.put(modelColumn, editor);
            String labelCell = "cell " + (i * 2) % (PANELS_NUMBER * 2) + " " + (i / PANELS_NUMBER);
            String editorCell = "cell " + (i * 2 + 1) % (PANELS_NUMBER * 2) + " " + (i / PANELS_NUMBER);
            addRecordPanel.add(label, labelCell);
            addRecordPanel.add(editor.getComponent(), editorCell + ", width 100%, wrap");

        }

        JButton addButton = new JButton("Добавить запись", new ImageIcon("src/main/resources/iconfinder_Properties_1493285.png"));
        buttonPanel.add(addButton);
        JButton clearButton = new JButton("Сбросить", new ImageIcon("src/main/resources/iconfinder_Undo_1493283.png"));
        buttonPanel.add(clearButton);
        addButton.addActionListener(e -> {
            Map<Integer, Object> addRecordMap = new HashMap<>();
            Set<Integer> keySet = editorList.keySet();
            for (Integer addColumn : keySet) {
                Object addObject = editorList.get(addColumn).getValue();
                if (tableInfo.isRequired(addColumn)) {
                    if (null == addObject) {
                        showInfoMessage("Надо заполнить обязательные поля", "Error in requied field");
                        return;
                    }
                }

                if (null != validators) {
                    Validator validator = validators.getValidator(addColumn);
                    if (null != validator) {
                        ValidationResult validationResult = validator.validate(addObject);
                        assert (null != validationResult);
                        if (!validationResult.isValid()) {
                            String message = new StringBuilder().append("Ошибка в поле ").
                                    append(columnsNameMap.get(addColumn)).append(":\n").
                                    append(validationResult.getMessage()).toString();
                            showInfoMessage(message, "Ошибка в поле " + columnsNameMap.get(addColumn));
                            return;
                        }
                    }
                }
                addRecordMap.put(addColumn, addObject);
            }

            try {
                tableModel.addRecord(addRecordMap);
            } catch (SQLException e1) {
                showErrorMessage("Ошибка добавления записи " + e1.getMessage() + ": " + addRecordMap.get(0), "Error add record");
            } finally {
                tableView.revalidate();
                tableView.repaint();
            }
        });

        clearButton.addActionListener(e -> {
            Set<Integer> keySet = editorList.keySet();
            for (Integer addColumn : keySet) {
                Editor editor = editorList.get(addColumn);
                assert (null != editor);
                editor.resetValue();
            }
        });

        return mainPanel;
    }

    private Editor buildEditor(int modelColumn) {
        TableNewRecordEditors editors = table.getNewRecordEditors();
        if (null != editors) {
            Editor addRecordEditor = editors.getEditor(modelColumn);
            if (null != addRecordEditor) {
                return addRecordEditor;
            }
        }

        TableDependencies dependencies = table.getTableInfo().getDependencies();


        if (null != dependencies) {
            if (dependencies.isForeignKey(modelColumn)) {
                JComboBox foreignKeyCombobox = createForeignKeyCombobox(dependencies, modelColumn);
                Editor editor = new Combobox(foreignKeyCombobox);
                return editor;
            }
        }

        Class columnClass = tableModel.getModelColumnClass(modelColumn);
        if (columnClass.equals(Integer.class)) {
            return new FormattedTextField();
        } else if (columnClass.equals(Timestamp.class)) {
            return new FormattedTextField(timeStampFormat);
        } else if (columnClass.equals(java.sql.Date.class)) {
            return new FormattedTextField(timeStampFormat);
        }
        return new TextFieldEditor();

    }

    private void addTable() {
        JScrollPane scrollPane = new JScrollPane(tableView);
        add(scrollPane, "width 100%, height 90%, wrap");
    }

    private void createTable() {
        tableView = new JTable(tableModel);
        tableView.setFillsViewportHeight(true);
        setEditorsAndRenders();
        addTableListeners();
    }

    private void addTableListeners() {
        assert (null != tableView);
        tableView.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (isRightMouseButton(e)) {
                    JTable source = (JTable) e.getSource();
                    final int row = source.rowAtPoint(e.getPoint());
                    final int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem("Удалить запись");
                    menuItem.addActionListener(e12 -> {
                        try {
                            tableModel.deleteRow(row);
                        } catch (SQLException e1) {
                           e1.printStackTrace();
                        } finally {
                            tableView.repaint();
                        }
                    });
                    popup.add(menuItem);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    private void setEditorsAndRenders() {
        TableDependencies dependencies = table.getTableInfo().getDependencies();
        int columnCount = table.getTableInfo().getDisplayColumns().size();

        if (null != dependencies) {
            for (int i = 0; i < columnCount; i++) {
                int modelColumn = table.getTableInfo().displayColumnToTableColumn(i);
                if (dependencies.isForeignKey(modelColumn)) {

                    JComboBox lookupCombobox = createForeignKeyCombobox(dependencies, modelColumn);
                    TableColumn column = tableView.getColumnModel().getColumn(i);
                    column.setCellEditor(new DefaultCellEditor(lookupCombobox));

                }
            }
        }

        TableEditors editors = table.getEditors();
        TableRenders renders = table.getRenders();

        for (int i = 0; i < columnCount; i++) {
            int modelColumn = table.getTableInfo().displayColumnToTableColumn(i);

            TableColumn column = tableView.getColumnModel().getColumn(i);

            TableCellEditor editor = editors.getEditor(modelColumn);
            if (editor != null) {
                column.setCellEditor(editor);
            }

            TableCellRenderer render = renders.getRender(modelColumn);
            if (render != null) {
                column.setCellRenderer(render);
            }
        }

    }

    private JComboBox createForeignKeyCombobox(TableDependencies dependencies, int modelColumn) {
        Map<Integer, String> lookupValues = dependencies.getLookupValues(modelColumn);
        assert (null != lookupValues);
        JComboBox lookupCombobox = new JComboBox();
        Set<Integer> keys = lookupValues.keySet();
        for (Integer key : keys) {
            LookupEntry entry = new LookupEntry(key, lookupValues.get(key));
            lookupCombobox.addItem(entry);
        }
        return lookupCombobox;
    }

    private JPanel createTableButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout("", "[]20[]"));
        JButton saveButton = new JButton("Сохранить изменения", new ImageIcon("src/main/resources/iconfinder_Save_1493294.png"));
        JButton cancelButton = new JButton("Отменить изменения", new ImageIcon("src/main/resources/iconfinder_Delete_1493279.png"));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        saveButton.addActionListener(e -> {
            try {
                tableModel.saveChanges();
            } catch (SyncProviderException e1) {
                try {
                    tableModel.discardChanges();
                } catch (SQLException e2) {
                   e2.printStackTrace();
                }
                showErrorMessage("Ошибка сохранения изменений.\n" +
                        "Причина: " + e1.getMessage(), "Ошибка сохранения изменений");
            } finally {
                tableView.revalidate();
                tableView.repaint();
            }
        });

        cancelButton.addActionListener(e -> {
            try {
                tableModel.discardChanges();

            } catch (SQLException e1) {
                showErrorMessage("Ошибка отмены изменений.\n" +
                        "Причина: " + e1.getMessage(), "Ошибка отмены изменений");

            } finally {
                tableView.repaint();
            }
        });
        return buttonPanel;
    }

    private void showErrorMessage(String message, String title) {
        showMessage(message, title, JOptionPane.ERROR_MESSAGE);

    }

    private void showInfoMessage(String message, String title) {
        showMessage(message, title, JOptionPane.INFORMATION_MESSAGE);

    }

    private void showMessage(String message, String title, int messageType) {
        Window currentComponent = SwingUtilities.getWindowAncestor(TableVisual.this);
        if (null != currentComponent) {

            JOptionPane.showMessageDialog(currentComponent, message, title, messageType);
        }
    }
}
