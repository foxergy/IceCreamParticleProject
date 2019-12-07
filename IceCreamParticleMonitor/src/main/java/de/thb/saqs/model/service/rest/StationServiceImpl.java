package de.thb.saqs.model.service.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import de.thb.saqs.model.domain.Station;

public class StationServiceImpl implements StationService {
    private Gson gson;
    private HttpClient client;
    private static final String URL = "http://localhost:8080";

    public StationServiceImpl( ){
        gson = new Gson();
        client = HttpClient.newHttpClient();
    }

    public Station getStationById(String stationId) throws IllegalArgumentException, IOException{
        return gson.fromJson(readJsonFromUrl("station/"+stationId), Station.class);
    }

    public List<Station> getAllStations() throws IOException{
        return Arrays.asList(gson.fromJson(readJsonFromUrl("stations"), Station[].class));
    }

    @Override
    public List<String> getAllStationIds() throws IOException{
        List<String> stationIds = new ArrayList<>();
        getAllStations().forEach(station -> {
            if(station!=null){
                stationIds.add(station.getStationId());
            }
        });
        return stationIds;
    }

    public Station createStation(Station station) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(station)))
                .build();
        return gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), Station.class);
    }

    public Station updateStation(Station station) throws IOException, InterruptedException {
        String content = gson.toJson(station);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+"/station/"+station.getStationId()))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(content))
                .build();
        return gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), Station.class);

    }

    private static String readJsonFromUrl(String urlString) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(URL+"/"+urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
