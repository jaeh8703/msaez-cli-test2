package petstore.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import petstore.PetApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { PetApplication.class })
public class CucumberSpingConfiguration {}
