package de.thb.saqs;

import de.thb.saqs.controller.FormController;
import de.thb.saqs.controller.ListController;
import de.thb.saqs.model.Model;
import de.thb.saqs.view.View;

public class Main {

    public static void main(String[] args){
            Model model = new Model();
            View view = new View();
            ListController listController = new ListController(model, view);
            FormController formController = new FormController(model, view);
            model.getWebSocketService().getStompSessionHandler().addObserver(listController);
    }
}
