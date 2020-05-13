package ru.nsu.ccfit.radeev.commonclient.database.framework.core.visual;

import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.radeev.commonclient.database.framework.core.logic.QueryLogic;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries.Query;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QueryVisual extends JPanel {
    private static final int PANELS_NUMBER = 2;
    private final Query query;
    private JPanel paramsPanel;
    private JPanel buttonPanel;
    private JPanel tablePanel;
    private QueryLogic model;
    private JTable tableView;

    public QueryVisual(Query query) {
        assert (null != query);
        this.query = query;
        assert (null != query.getQuery());
        model = new QueryLogic(query.getQuery());
        buildInterface();
    }

    private void buildInterface() {
        setLayout(new MigLayout());
        JLabel infoLabel = new JLabel(query.getDescription());
        add(infoLabel, "width 50%, wrap");
        buildParamsPanel();
        tablePanel = new JPanel();
        tablePanel.setLayout(new MigLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Результат"));
        add(tablePanel, "width 100%");
    }


    private void buildParamsPanel() {
        paramsPanel = new JPanel();
        paramsPanel.setLayout(new MigLayout());
        paramsPanel.setBorder(BorderFactory.createTitledBorder("Настраиваемые параметры"));
        add(paramsPanel, "width 100%, wrap");

        final Map<Integer, Editor> editorList = new HashMap<>();

        int columnCount = query.getParamNames().size();
        for (int i = 0; i < columnCount; i++) {
            String labelName = query.getParamNames().get(i);
            JLabel label = new JLabel(labelName);

            Editor editor = buildEditor(i);
            assert (null != editor);
            editorList.put(i, editor);
            String labelCell = "cell " + (i * 2) % (PANELS_NUMBER * 2) + " " + (i / PANELS_NUMBER);
            String editorCell = "cell " + (i * 2 + 1) % (PANELS_NUMBER * 2) + " " + (i / PANELS_NUMBER);
            paramsPanel.add(label, labelCell);
            paramsPanel.add(editor.getComponent(), editorCell + ", width 100, wrap");
        }

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout());
        add(buttonPanel, "alignx right, wrap");

        JButton executeButton = new JButton("Исполнить");
        JButton clearButton = new JButton("Очистить");

        buttonPanel.add(executeButton);
        buttonPanel.add(clearButton);

        clearButton.addActionListener(e -> {
            Set<Integer> keySet = editorList.keySet();
            for (Integer key : keySet) {
                Editor editor = editorList.get(key);
                editor.resetValue();
            }
        });

        executeButton.addActionListener(e -> {
            Map<Integer, Object> params = new HashMap<>();
            Set<Integer> keySet = editorList.keySet();
            for (Integer key : keySet) {
                Editor editor = editorList.get(key);
                Object value = editor.getValue();
                params.put(key, value);
            }

            try {
                model.getData(params);
            } catch (SQLException e1) {
                showErrorMessage("Ошибка получения данных " + e1.getMessage(), "Error get data");
                return;
            }

            tablePanel.setVisible(false);
            tablePanel.removeAll();
            if (model.getRowCount() <= 0) {
                tablePanel.add(new JLabel("Результат пуст"));
            } else {
                tableView = new JTable(model);
                tableView.setFillsViewportHeight(true);
                tablePanel.add(tableView, "width 100%");
            }
            tablePanel.setVisible(true);
        });

    }

    private Editor buildEditor(int i) {
        return query.getEditors().getEditor(i);
    }

    private void showErrorMessage(String message, String title) {
        showMessage(message, title, JOptionPane.ERROR_MESSAGE);

    }

    private void showInfoMessage(String message, String title) {
        showMessage(message, title, JOptionPane.INFORMATION_MESSAGE);

    }

    private void showMessage(String message, String title, int messageType) {
        Window currentComponent = SwingUtilities.getWindowAncestor(QueryVisual.this);
        if (null != currentComponent) {

            JOptionPane.showMessageDialog(currentComponent, message, title, messageType);
        }
    }
}
