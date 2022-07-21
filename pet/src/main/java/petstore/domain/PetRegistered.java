package petstore.domain;

import java.util.*;
import lombok.*;
import petstore.domain.*;
import petstore.infra.AbstractEvent;

@Data
@ToString
public class PetRegistered extends AbstractEvent {

    private Long id;
    private String name;
    private Integer energy;
    private Integer appearance;
    private String photo;
    private Integer weight;
    private String address;
    private String type;
    private String illnessHistory;

    public PetRegistered(Pet aggregate) {
        super(aggregate);
    }

    public PetRegistered() {
        super();
    }
    // keep

}
