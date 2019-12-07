package de.thb.saqs.view.panel;

import de.thb.saqs.view.components.VarianceField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class FormPanel extends JPanel {

    private JLabel stationIdLabel;
    private JLabel dateLabel;
    private JLabel targetLabel;
    private JLabel actualLabel;
    private JLabel varianceLabel;

    private JFormattedTextField stationIdField;
    private JFormattedTextField dateField;
    private JFormattedTextField targetField;
    private JFormattedTextField actualField;
    private VarianceField varianceField;
    //SingleBarChart singleBarChart;

    public FormPanel(){
        setLayout(new GridBagLayout());
        setLabels();
        setBackground(Color.WHITE);
    }

    private void initLabels(){
        Font font = new Font("Courier", Font.BOLD,13);
        stationIdLabel = new JLabel("Station ID", SwingConstants.LEFT);
        stationIdLabel.setFont(font);
        dateLabel = new JLabel("Date", SwingConstants.LEFT);
        dateLabel.setFont(font);
        targetLabel = new JLabel("Target", SwingConstants.LEFT);
        targetLabel.setFont(font);
        actualLabel = new JLabel("Actual", SwingConstants.LEFT);
        actualLabel.setFont(font);
        varianceLabel = new JLabel("Variance", SwingConstants.LEFT);
        varianceLabel.setFont(font);
        varianceLabel.setFont(font);
    }

    private void initFormattedTextFields(){
        stationIdField = new JFormattedTextField();
        stationIdField.setPreferredSize(new Dimension(120, 20));
        dateField = new JFormattedTextField();
        targetField = new JFormattedTextField();
        actualField = new JFormattedTextField();
        varianceField = new VarianceField(-34);
    }

    private void setLabels(){
        initLabels();
        setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, 20,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx= 100;
        c.gridx=0;
        c.gridy=0;
        add(stationIdLabel, c);
        c.gridy=1;
        add(dateLabel, c);
        c.gridy=2;
        add(targetLabel, c);
        c.gridy=3;
        add(actualLabel, c);
        c.gridy=4;
        add(varianceLabel, c);

        initFormattedTextFields();
        c.gridx=1;
        c.gridy=0;
        add(stationIdField, c);
        c.gridy=1;
        add(dateField, c);
        c.gridy=2;
        add(targetField, c);
        c.gridy=3;
        add(actualField, c);
        c.gridy=4;
        add(varianceField, c);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        //c.gridy=4;
        //SingleBarChart singleBarChart = new SingleBarChart(Color.GREEN, 890, 900);
        //add(singleBarChart, c);
        //setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 30));
    }
}
