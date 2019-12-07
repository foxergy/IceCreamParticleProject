package de.thb.saqs.web;

import de.thb.saqs.model.Station;
import de.thb.saqs.model.WebSocketMessage;
import de.thb.saqs.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;


@Controller
@Slf4j
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping(value = "/station/{stationId}")
    public ResponseEntity<Station> getStation(@PathVariable("stationId") String id){
        try {
            return new ResponseEntity<>(stationService.getStationById(id), HttpStatus.OK);
        }catch (FileNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/stations")
    public ResponseEntity<Collection<Station>>  getStations(){
        try {
            return new ResponseEntity<>(stationService.getAllStations(), HttpStatus.OK);
        }catch (FileNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "stations",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Station> postStation(@RequestBody Station station){
        try{
            return new ResponseEntity<>(stationService.createStation(station), HttpStatus.CREATED);
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/station/{stationId}")
    public ResponseEntity<Station> updateStation(@PathVariable("stationId") String id, @RequestBody Station station){
        try{
            return new ResponseEntity<>(stationService.updateStation(station), HttpStatus.ACCEPTED);
        }catch (IOException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @MessageMapping("/stationSocket")
    @SendTo("/topic/observable")
    private WebSocketMessage notifyStationsUpdate(WebSocketMessage webSocketMessage){
        log.info("Called notifyStationsUpdate");
        return webSocketMessage;
    }
}
