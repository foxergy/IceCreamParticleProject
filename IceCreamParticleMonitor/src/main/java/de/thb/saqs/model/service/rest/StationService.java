package de.thb.saqs.model.service.rest;

import java.io.IOException;
import java.util.List;
import de.thb.saqs.model.domain.Station;

public interface StationService {

    Station getStationById(String stationId) throws IOException;

    List<Station> getAllStations() throws IOException;

    List<String> getAllStationIds() throws IOException;

    Station createStation(Station station) throws IOException, InterruptedException;

    Station updateStation(Station station) throws IOException, InterruptedException;
}
