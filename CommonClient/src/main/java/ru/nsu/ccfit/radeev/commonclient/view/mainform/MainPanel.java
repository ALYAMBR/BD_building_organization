package ru.nsu.ccfit.radeev.commonclient.view.mainform;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel(){
        setLayout(new MigLayout());
    }

    public void addComponent(JComponent component){
        assert(component != null);
        try{
            setVisible(false);
            removeAll();
            add(component, "width 100%, height 100%");
        }finally{
            setVisible(true);
        }
    }
}
