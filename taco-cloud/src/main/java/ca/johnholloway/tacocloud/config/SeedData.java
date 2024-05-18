package ca.johnholloway.tacocloud.config;

import ca.johnholloway.tacocloud.model.Ingredient;
import ca.johnholloway.tacocloud.model.Taco;
import ca.johnholloway.tacocloud.model.TacoUser;
import ca.johnholloway.tacocloud.repository.IngredientRepository;
import ca.johnholloway.tacocloud.repository.TacoRepository;
import ca.johnholloway.tacocloud.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
This class is to populate the database with stuff upon running.

 */
@Component
public class SeedData implements CommandLineRunner{


    private IngredientRepository repo;
    private TacoRepository tacoRepository;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SeedData(IngredientRepository ingredientRepository,
                    TacoRepository tacoRepository,
                    UserRepository userRepository,
                    PasswordEncoder passwordEncoder){
        this.repo = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception{

        /*
           repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
           repo.save(new Ingredient("COTO", "Corn Torilla", Ingredient.Type.WRAP));
           repo.save(new Ingredient("GRBF", "Gound Beef", Ingredient.Type.PROTEIN));
           repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
           repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
           repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
           repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
           repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
           repo.save(Ingredient salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
           repo.save(Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
         */
           Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
           Ingredient cornTortilla = new Ingredient("COTO", "Corn Torilla", Ingredient.Type.WRAP);
           Ingredient groundBeef = new Ingredient("GRBF", "Gound Beef", Ingredient.Type.PROTEIN);
           Ingredient carnitas = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
           Ingredient dicedTomatoes = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
           Ingredient lettuce = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
           Ingredient cheddar = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
           Ingredient monterrey = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
           Ingredient salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
           Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);

           repo.save(flourTortilla);
           repo.save(cornTortilla);
           repo.save(groundBeef);
           repo.save(carnitas);
           repo.save(dicedTomatoes);
           repo.save(lettuce);
           repo.save(cheddar);
           repo.save(monterrey);
           repo.save(salsa);
           repo.save(sourCream);

           Taco taco1 = new Taco();
           taco1.setName("Meaty Boy!");
           taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, salsa, cheddar));
           tacoRepository.save(taco1);


           Taco taco2 = new Taco();
           taco2.setIngredients(Arrays.asList(flourTortilla, cornTortilla, dicedTomatoes));
           taco2.setName("VeggieGoodness");
           tacoRepository.save(taco2);

           Taco taco3 = new Taco();
           taco3.setIngredients(Arrays.asList(cheddar, monterrey, sourCream));
           taco3.setName("CheesyGoodness");
           tacoRepository.save(taco3);

           for(int i = 0; i < 20; i++){
               Taco t = new Taco();
               t.setName("AutoTaco " + String.valueOf(i));
               t.setIngredients(Arrays.asList(cheddar, monterrey));
               tacoRepository.save(t);
           }

           //I swear to God if I have to manually create a new user for everytime I rebuild the app I will go insane!!
           TacoUser tacoUser = new TacoUser("john", passwordEncoder.encode("password"), "John Holloway", "123 Fake Street", "CityVille", "Narnia", "11111", "555-867-5309");
           userRepository.save(tacoUser);

            System.out.println("public void run() has completed");
    }


}
