package ca.johnholloway.tacocloud.controller;

import ca.johnholloway.tacocloud.model.Ingredient;
import ca.johnholloway.tacocloud.model.Ingredient.Type;
import ca.johnholloway.tacocloud.model.Taco;
import ca.johnholloway.tacocloud.model.TacoOrder;

import ca.johnholloway.tacocloud.repository.IngredientRepository;
import ca.johnholloway.tacocloud.udt.TacoUDRUtils;
import ca.johnholloway.tacocloud.udt.TacoUDT;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
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
            log.info("Error with taco: {}", taco);
            return "design";
        }

        tacoOrder.addTaco(new TacoUDT(taco.getId(), taco.getName(),taco.getCreatedAt(), taco.getIngredients()));
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}
