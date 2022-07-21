package petstore.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import petstore.PetApplication;
import petstore.domain.PetRegistered;

@Entity
@Table(name = "Pet_table")
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Photo photo;

    private String name;

    private Integer energy;

    private Integer appearance;

    private Integer weight;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ElementCollection
    private List<IllnessHistory> illnessHistory;

    private String test123;

    @PostPersist
    public void onPostPersist() {
        PetRegistered petRegistered = new PetRegistered(this);
        petRegistered.publishAfterCommit();
    }

    public static PetRepository repository() {
        PetRepository petRepository = PetApplication.applicationContext.getBean(
            PetRepository.class
        );
        return petRepository;
    }

    public void feed() {
        Overweighted overweighted = new Overweighted(this);
        overweighted.publishAfterCommit();

        Fed fed = new Fed(this);
        fed.publishAfterCommit();
    }

    public void groom() {}

    public void comb() {}
}
