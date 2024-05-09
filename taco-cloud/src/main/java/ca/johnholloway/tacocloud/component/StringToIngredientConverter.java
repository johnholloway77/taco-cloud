package ca.johnholloway.tacocloud.component;

import ca.johnholloway.tacocloud.model.Ingredient;
import ca.johnholloway.tacocloud.repository.IngredientRepository;
import ca.johnholloway.tacocloud.udt.IngredientUDT;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if(ingredient.isEmpty()){
            return null;
        }

        return ingredient.map(i -> {
            return new IngredientUDT(i.getName(), i.getType());
        }).get();
    }
}