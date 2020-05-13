package ru.nsu.ccfit.radeev.commonclient.view.mainform;

import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.radeev.commonclient.view.utils.Action;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class HierarchyTreePanel extends JPanel {
    private static final String TABLES_DESCRIPTION_PACKAGE = "ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.";
    private static final String QUERIES_DESCRIPTION_PACKAGE = "ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.";

    private JTree tree;
    private final MainFrame mainFrame;

    public HierarchyTreePanel(final MainFrame mainFrame){
        assert(mainFrame != null);
        this.mainFrame = mainFrame;

        setLayout(new MigLayout());
        JLabel infoLabel = new JLabel("Выберите действие:");
        add(infoLabel, "width 100%, wrap");
        initTree();
        JScrollPane treeView = new JScrollPane(tree);
        add(treeView, "width 100%, height 100%");
    }

    private void initTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Строительная организация");
        createNodes(top);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if(node == null){
                return;
            }

            Object nodeInfo = node.getUserObject();
            if(node.isLeaf()){
                Action action = (Action)nodeInfo;
                HierarchyTreePanel.this.mainFrame.processAction(action);
            }
        });
    }
//TODO write right categories
    private void createNodes(DefaultMutableTreeNode top){
        DefaultMutableTreeNode category = new DefaultMutableTreeNode("Таблицы");
        top.add(category);
        category.add(new DefaultMutableTreeNode(new Action("Заказчики",
                TABLES_DESCRIPTION_PACKAGE+"customer.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Сотрудники",
                TABLES_DESCRIPTION_PACKAGE+"employee.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Отделы",
                TABLES_DESCRIPTION_PACKAGE+"department.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Специалисты",
                TABLES_DESCRIPTION_PACKAGE+"workers.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Специализации",
                TABLES_DESCRIPTION_PACKAGE+"typeworker.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Бригады",
                TABLES_DESCRIPTION_PACKAGE+"brigade.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Участки",
                TABLES_DESCRIPTION_PACKAGE+"district.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Рабочие",
                TABLES_DESCRIPTION_PACKAGE+"slave.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Группы специалистов",
                TABLES_DESCRIPTION_PACKAGE+"groupworkers.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Начальники участков",
                TABLES_DESCRIPTION_PACKAGE+"depdistrictconnections.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Строительные объекты",
                TABLES_DESCRIPTION_PACKAGE+"buildingobject.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Категории объектов",
                TABLES_DESCRIPTION_PACKAGE+"objectcategories.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Техника",
                TABLES_DESCRIPTION_PACKAGE+"technics.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Договора",
                TABLES_DESCRIPTION_PACKAGE+"agreement.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Смета",
                TABLES_DESCRIPTION_PACKAGE+"estimate.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Работы",
                TABLES_DESCRIPTION_PACKAGE+"works.Show")));

        category.add(new DefaultMutableTreeNode(new Action("История техники",
                TABLES_DESCRIPTION_PACKAGE+"technicshistory.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Виды работ",
                TABLES_DESCRIPTION_PACKAGE+"typeworks.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Виды рабочих",
                TABLES_DESCRIPTION_PACKAGE+"typeslave.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Виды объектов",
                TABLES_DESCRIPTION_PACKAGE+"typeobject.Show")));

        category = new DefaultMutableTreeNode("Запросы");
        top.add(category);
        category.add(new DefaultMutableTreeNode(new Action("Начальники отделов",
                QUERIES_DESCRIPTION_PACKAGE + "departmentbosses.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Начальники участков",
                QUERIES_DESCRIPTION_PACKAGE + "districtbosses.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Специалисты",
                QUERIES_DESCRIPTION_PACKAGE + "workersindistanddeps.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Объекты",
                QUERIES_DESCRIPTION_PACKAGE + "objectsindistanddep.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Бригады на объекте",
                QUERIES_DESCRIPTION_PACKAGE + "brigadesonobject.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Техника в отделе",
                QUERIES_DESCRIPTION_PACKAGE + "technicsindep.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Техника на объекте",
                QUERIES_DESCRIPTION_PACKAGE + "technicsonobject.Show")));

        category.add(new DefaultMutableTreeNode(new Action("Смета и работы",
                QUERIES_DESCRIPTION_PACKAGE + "estimateandworksobject.Show")));
    }
}
