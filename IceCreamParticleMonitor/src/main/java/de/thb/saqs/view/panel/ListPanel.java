package de.thb.saqs.view.panel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListPanel extends JPanel
{
    private JList stationList;

    public ListPanel() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(80,10, 70,5));
        setBackground(Color.WHITE);

        this.stationList = new JList();
        this.stationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.stationList.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollPane = new JScrollPane(this.stationList);
        scrollPane.setPreferredSize(new Dimension(150, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrollPane);
    }
}
