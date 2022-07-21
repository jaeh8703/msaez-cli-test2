package petstore.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import petstore.domain.*;

@RestController
// @RequestMapping(value="/pets")
@Transactional
public class PetController {

    @Autowired
    PetRepository petRepository;

    @RequestMapping(
        value = "pets/{id}/feed",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Pet feed(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pet/feed  called #####");
        Optional<Pet> optionalPet = petRepository.findById(id);

        optionalPet.orElseThrow(() -> new Exception("No Entity Found"));
        Pet pet = optionalPet.get();
        pet.feed();

        petRepository.save(pet);
        return pet;
    }

    @RequestMapping(
        value = "pets/{id}/groom",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Pet groom(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pet/groom  called #####");
        Optional<Pet> optionalPet = petRepository.findById(id);

        optionalPet.orElseThrow(() -> new Exception("No Entity Found"));
        Pet pet = optionalPet.get();
        pet.groom();

        petRepository.save(pet);
        return pet;
    }

    @RequestMapping(
        value = "pets/{id}/comb",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Pet comb(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pet/comb  called #####");
        Optional<Pet> optionalPet = petRepository.findById(id);

        optionalPet.orElseThrow(() -> new Exception("No Entity Found"));
        Pet pet = optionalPet.get();
        pet.comb();

        petRepository.save(pet);
        return pet;
    }
    // keep
}
