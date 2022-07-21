package petstore.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import petstore.domain.*;

@Component
public class ItemHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Item>> {

    @Override
    public EntityModel<Item> process(EntityModel<Item> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/adopt")
                .withRel("adopt")
        );

        return model;
    }
}
