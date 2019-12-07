package de.thb.saqs.view;

import de.thb.saqs.view.panel.FormPanel;
import de.thb.saqs.view.panel.ListPanel;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class View {
    private ListPanel listPanel;
    private FormPanel formPanel;

    public View(){
        JFrame mainFrame = new JFrame("IceCreamParticleMonitor");

        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(new BorderLayout());

        this.listPanel = new ListPanel();
        layoutPanel.add(listPanel, BorderLayout.WEST);


        this.formPanel = new FormPanel();
        layoutPanel.add(formPanel, BorderLayout.CENTER);

        mainFrame.setContentPane(layoutPanel);
        mainFrame.setSize(450,400);
        mainFrame.setVisible(true);
    }
}
