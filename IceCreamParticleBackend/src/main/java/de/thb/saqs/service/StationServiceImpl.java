package de.thb.saqs.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import de.thb.saqs.model.Station;
import de.thb.saqs.model.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static de.thb.saqs.model.ChangedType.CREATED;
import static de.thb.saqs.model.ChangedType.UPDATED;

@Service
@Slf4j
public class StationServiceImpl implements StationService {

    private File file;
    private Gson gson;
    private static final String FILENAME="src\\main\\resources\\data.json";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public Station getStationById(String id) throws FileNotFoundException{
        for(Station station : getListFromFile()){
            if (station.getStationId().contains(id)){
                return station;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<Station> getAllStations() throws FileNotFoundException {
        return Arrays.asList(getListFromFile());
    }

    @Override
    public Station updateStation(Station station) throws FileNotFoundException, IOException  {
        List<Station> stations = Arrays.asList(getListFromFile());
        if(!stations.contains(station)) {
            int index = -1;
            for (Station station1 : stations) {
                if (station1.getStationId().equals(station.getStationId())) {
                    index = stations.indexOf(station1);
                }
            }
            if (index != -1) {
                stations.set(index, station);
            } else {
                throw new IllegalArgumentException();
            }
            writeToFile(stations);
            simpMessagingTemplate.convertAndSend("/topic/observable",  new WebSocketMessage(station, UPDATED));
            return station;
        }else {
            return null;
        }
    }

    @Override
    public Station createStation(Station station) throws FileNotFoundException, IOException {
        List<Station> stations = new ArrayList<Station>(Arrays.asList(getListFromFile()));
        stations.add(station);
        writeToFile(stations);
        simpMessagingTemplate.convertAndSend("/topic/observable",  new WebSocketMessage(station, CREATED));
        return station;
    }

    private void writeToFile(List<Station> stations) throws IOException{
        try(Writer writer = new FileWriter(FILENAME)){
            gson.toJson(stations, writer);
            writer.flush();
        }
    }

    private Station[] getListFromFile() throws FileNotFoundException{
        gson = new Gson();
        File file = new File(FILENAME);
        JsonReader reader = new JsonReader(new FileReader(file.getAbsolutePath()));
        return gson.fromJson(reader, Station[].class);
    }



    @Scheduled(fixedRate = 30000)
    @Async
    public void addRandomStationAtRandomTime(){
        try {
            String stationId = RandomStringUtils.randomAlphabetic(2)+"-"+RandomStringUtils.random(3);
            String date = LocalDate.now().toString();

            Random random = new Random();
            int target = random.nextInt((100-5)+1)+5;
            int actual = target- random.nextInt((100-5)+1)+5;
            int variance=0;
            Station randomStation = new Station(stationId, date, target, actual, variance);
            log.info("Created Station: "+randomStation.toString());
            createStation(randomStation);
        }catch (IOException e){
        }
    }
}
