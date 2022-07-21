package petstore.domain;

import java.util.*;
import lombok.*;
import petstore.domain.*;
import petstore.infra.AbstractEvent;

@Data
@ToString
public class Dissoluted extends AbstractEvent {

    private Long id;
    private Double price;
    private String adopter;
    private String status;

    public Dissoluted(Item aggregate) {
        super(aggregate);
    }

    public Dissoluted() {
        super();
    }
    // keep

}
