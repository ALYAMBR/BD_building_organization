package ru.nsu.ccfit.radeev.commonclient.view.mainform;

import net.miginfocom.swing.MigLayout;
import ru.nsu.ccfit.radeev.commonclient.config.MainFrameConfig;
import ru.nsu.ccfit.radeev.commonclient.database.DatabaseConnector;
import ru.nsu.ccfit.radeev.commonclient.view.utils.Action;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private JPanel jPanel;

    public MainFrame(){
        super();

        setTitle("Строительная организация");
        setSize(MainFrameConfig.PREFERRED_DIMENSION);
        setMinimumSize(MainFrameConfig.MINIMUM_DIMENSION);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
                DatabaseConnector.dispose();
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        jPanel = new JPanel();
        jPanel.setLayout(new MigLayout());
        add(jPanel);
        initPanels();
    }

    private void initPanels() {
        HierarchyTreePanel hierarchyTreePanel = new HierarchyTreePanel(this);
        jPanel.add(hierarchyTreePanel, "width 20%, height 100%");

        mainPanel = new MainPanel();
        jPanel.add(mainPanel, "width 80%, height 100%");
    }

    void processAction(Action action){
        assert(action != null);
        String className = action.getClassName();
        try {
            Class createdClass = Class.forName(className);
            Object createdObject = createdClass.getDeclaredConstructor().newInstance();
            assert(createdObject instanceof MainShow);
            MainShow component = (MainShow) createdObject;
            mainPanel.addComponent(component.getRepresentation());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
