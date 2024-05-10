package ca.johnholloway.tacocloud.config;

import ca.johnholloway.tacocloud.model.Ingredient;
import ca.johnholloway.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/*
This class is to populate the database with stuff upon running.

 */
@Component
public class SeedData implements CommandLineRunner{


    @Autowired
    private IngredientRepository repo;

    @Override
    public void run(String... args) throws Exception{
           repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
           repo.save(new Ingredient("COTO", "Corn Torilla", Ingredient.Type.WRAP));
           repo.save(new Ingredient("GRBF", "Gound Beef", Ingredient.Type.PROTEIN));
           repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
           repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
           repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
           repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
           repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
           repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
           repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

            System.out.println("public void run() has completed");
    }


}
