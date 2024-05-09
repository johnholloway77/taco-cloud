package ca.johnholloway.tacocloud.udt;

import ca.johnholloway.tacocloud.model.Ingredient;
import ca.johnholloway.tacocloud.model.Taco;

import java.util.List;
import java.util.stream.Collectors;

public class TacoUDRUtils {

    public static TacoUDT toTacoUDT(Taco taco){
        return new TacoUDT(taco.getName(), taco.getIngredients());

    }

    public static IngredientUDT toIngredientUDT(Ingredient ingredient){
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }


    public static List<IngredientUDT> toIngredients(List<Ingredient> ingredients){
        return ingredients
                .stream()
                .map(ingredient -> toIngredientUDT(ingredient))
                .collect(Collectors.toList());
    }

}
