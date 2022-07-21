package petstore.domain;

import java.util.*;
import lombok.*;
import petstore.domain.*;
import petstore.infra.AbstractEvent;

@Data
@ToString
public class Fed extends AbstractEvent {

    private Long id;
    private String name;
    private Integer energy;
    private Integer appearance;
    private String status;

    public Fed(Pet aggregate) {
        super(aggregate);
    }

    public Fed() {
        super();
    }
    // keep

}
