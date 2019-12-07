package de.thb.saqs.service;

import de.thb.saqs.model.Station;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface StationService {

    Station getStationById(String id) throws FileNotFoundException;

    Collection<Station> getAllStations() throws FileNotFoundException;

    Station updateStation(Station station) throws FileNotFoundException, IOException;

    Station createStation(Station station) throws FileNotFoundException, IOException;

    void addRandomStationAtRandomTime();
}
