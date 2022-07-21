package petstore.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import petstore.StoreApplication;
import petstore.domain.Adopted;
import petstore.domain.Dissoluted;

@Entity
@Table(name = "Item_table")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double price;

    private String adopter;

    private String status;

    @Embedded
    @AttributeOverride(
        name = "id",
        column = @Column(name = "petIdId", nullable = true)
    )
    private PetId petId;

    @PostPersist
    public void onPostPersist() {
        Adopted adopted = new Adopted(this);
        adopted.publishAfterCommit();

        Dissoluted dissoluted = new Dissoluted(this);
        dissoluted.publishAfterCommit();
    }

    public static ItemRepository repository() {
        ItemRepository itemRepository = StoreApplication.applicationContext.getBean(
            ItemRepository.class
        );
        return itemRepository;
    }

    public void adopt() {}

    public static void 전시(PetRegistered petRegistered) {
        /** Example 1:  new item 
        Item item = new Item();
        repository().save(item);

        */

        /** Example 2:  finding and process
        
        repository().findById(petRegistered.get???()).ifPresent(item->{
            
            item // do something
            repository().save(item);


         });
        */

    }

    public static void update(Overweighted overweighted) {
        /** Example 1:  new item 
        Item item = new Item();
        repository().save(item);

        */

        /** Example 2:  finding and process
        
        repository().findById(overweighted.get???()).ifPresent(item->{
            
            item // do something
            repository().save(item);


         });
        */

    }
}
