package de.thb.saqs.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Station {

    private String stationId;

    //@JsonFormat(pattern = "dd-MM-yyyy")
    private String date;

    private int target;

    private int actual;

    private int variance;
}
