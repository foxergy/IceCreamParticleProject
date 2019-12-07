package de.thb.saqs.model.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station{

    private String stationId;
    private String date;
    private int target;
    private int actual;
    private int variance;

    @Override
    public String toString() {
        return "Station{" +
                "stationId='" + stationId + '\'' +
                ", date='" + date + '\'' +
                ", target=" + target +
                ", actual=" + actual +
                ", variance=" + variance +
                '}';
    }
}
