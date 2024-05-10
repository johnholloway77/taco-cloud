package ca.johnholloway.tacocloud.controller;

import ca.johnholloway.tacocloud.model.Ingredient;
import ca.johnholloway.tacocloud.model.Ingredient.Type;
import ca.johnholloway.tacocloud.model.Taco;
import ca.johnholloway.tacocloud.model.TacoOrder;

import ca.johnholloway.tacocloud.model.TacoUser;
import ca.johnholloway.tacocloud.repository.IngredientRepository;
import ca.johnholloway.tacocloud.repository.TacoRepository;
import ca.johnholloway.tacocloud.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.Errors;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    private final UserRepository userRepository;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository,
            UserRepository userRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){

        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));

        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @ModelAttribute(name = "tacoUser")
    public TacoUser tacoUser(Principal principal){
        String username = principal.getName();
        TacoUser tacoUser = userRepository.findByUsername(username);
        return tacoUser;
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(
            @Valid Taco taco,
            Errors errors,
            @ModelAttribute TacoOrder tacoOrder){
        if(errors.hasErrors()){
            return "design";
        }

        log.info("\t--saving taco: {}", taco);
        Taco save = tacoRepository.save(taco);

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}
