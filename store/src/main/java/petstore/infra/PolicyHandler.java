package petstore.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import petstore.config.kafka.KafkaProcessor;
import petstore.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ItemRepository itemRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PetRegistered'"
    )
    public void wheneverPetRegistered_전시(
        @Payload PetRegistered petRegistered
    ) {
        PetRegistered event = petRegistered;
        System.out.println(
            "\n\n##### listener 전시 : " + petRegistered + "\n\n"
        );

        // Sample Logic //
        Item.전시(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Overweighted'"
    )
    public void wheneverOverweighted_Update(
        @Payload Overweighted overweighted
    ) {
        Overweighted event = overweighted;
        System.out.println(
            "\n\n##### listener Update : " + overweighted + "\n\n"
        );

        // Sample Logic //
        Item.update(event);
    }
    // keep

}
