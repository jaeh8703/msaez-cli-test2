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
    private Photo photo;
    private Integer weight;
    private Address address;
    private Status status;
    private Type type;
    private List<IllnessHistory> illnessHistory;

    public Overweighted(Pet aggregate) {
        super(aggregate);
    }

    public Overweighted() {
        super();
    }
    // keep

}
