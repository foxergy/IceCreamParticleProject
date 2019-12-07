package de.thb.saqs.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebSocketMessage {
    private Station station;
    private ChangedType changedType;
}
