package de.thb.saqs.model;

import de.thb.saqs.model.service.rest.StationService;
import de.thb.saqs.model.service.rest.StationServiceImpl;
import de.thb.saqs.model.service.websocket.WebSocketService;
import lombok.Data;

@Data
public class Model{

    private StationService stationService;
    private WebSocketService webSocketService;

    public Model() {
        this.stationService=new StationServiceImpl();
        this.webSocketService=new WebSocketService();
    }
}
