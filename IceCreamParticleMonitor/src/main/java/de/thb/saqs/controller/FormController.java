package de.thb.saqs.controller;

import de.thb.saqs.model.Model;
import de.thb.saqs.model.domain.Station;
import de.thb.saqs.view.View;
import de.thb.saqs.view.panel.FormPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;

public class FormController {

    public FormController(Model model, View view){
        FormPanel formPanel = view.getFormPanel();
        formPanel.getActualField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.print(e.getType());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.print(e.getType());
                updateField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.print(e.getType());
            }

            private void updateField(){
                try {
                    model.getStationService().updateStation(new Station(
                            (String) formPanel.getStationIdField().getValue(),
                            (String) formPanel.getDateField().getValue(),
                            (int) formPanel.getTargetField().getValue(),
                            (int) formPanel.getActualField().getValue(),
                            Integer.parseInt(formPanel.getVarianceField().getText())
                    ));
                }catch (IOException | InterruptedException exception){
                    JOptionPane optionPane = new JOptionPane(exception.getMessage(), JOptionPane.ERROR_MESSAGE);
                    optionPane.setVisible(true);
                }
            }
        });
    }
}
