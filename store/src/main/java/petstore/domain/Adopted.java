package petstore.domain;

import java.util.*;
import lombok.*;
import petstore.domain.*;
import petstore.infra.AbstractEvent;

@Data
@ToString
public class Adopted extends AbstractEvent {

    private Long id;
    private Double price;
    private String adopter;
    private String status;

    public Adopted(Item aggregate) {
        super(aggregate);
    }

    public Adopted() {
        super();
    }
    // keep

}
