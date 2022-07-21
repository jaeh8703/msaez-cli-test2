package petstore.domain;

import java.util.*;
import lombok.*;
import petstore.domain.*;
import petstore.infra.AbstractEvent;

@Data
@ToString
public class Overweighted extends AbstractEvent {

    private Long id;
    private String name;
    private Integer energy;
    private Integer appearance;
    private Object photo;
    private Integer weight;
    private Object address;
    private Object status;
    private Object type;
    private Object illnessHistory;
    // keep

}
