package de.thb.saqs.controller;

import de.thb.saqs.model.Model;
import de.thb.saqs.model.domain.Station;
import de.thb.saqs.model.domain.WebSocketMessage;
import de.thb.saqs.view.View;

import javax.swing.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ListController implements Observer {

    private View view;
    private Model model;

    public ListController(Model model, View view){
        this.model=model;
        this.view=view;
        initializeList();

        view.getListPanel().getStationList().addListSelectionListener(e -> {
            Station selectedStation = null;
            try {
                String selectedStationId = view.getListPanel().getStationList().getSelectedValue()+"";
                selectedStation = model.getStationService().getStationById(selectedStationId);
                System.out.println(selectedStation.toString());
            }catch (IOException ex){
                JOptionPane optionPane = new JOptionPane(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                optionPane.setVisible(true);
            }
            if(selectedStation!=null) {
                view.getFormPanel().getStationIdField().setValue(selectedStation.getStationId());
                view.getFormPanel().getDateField().setValue(selectedStation.getDate());
                view.getFormPanel().getTargetField().setValue(selectedStation.getTarget());
                view.getFormPanel().getActualField().setValue(selectedStation.getActual());
                view.getFormPanel().getVarianceField().setText(selectedStation.getVariance() + "");

            }else{
                JOptionPane.showMessageDialog(null,"The station with id: "+ selectedStation + " was not found");
            }
        });
    }

    private void initializeList(){
        try{
            view.getListPanel().getStationList().setListData(model.getStationService().getAllStationIds().toArray());
        }catch (IOException e){
            JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
            optionPane.setVisible(true);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        WebSocketMessage message = (WebSocketMessage) arg;
        System.out.println("The item: "+ message.getStation().getStationId()+" was "+message.getChangedType());
        switch(message.getChangedType()){
            case CREATED:
                try{
                    view.getListPanel().getStationList().setListData(model.getStationService().getAllStationIds().toArray());
                }catch (IOException e){
                    JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                    optionPane.setVisible(true);
                }
                break;
            case UPDATED:
                //UPDATE ELEMENT IN LIST
                break;
        }

    }
}
