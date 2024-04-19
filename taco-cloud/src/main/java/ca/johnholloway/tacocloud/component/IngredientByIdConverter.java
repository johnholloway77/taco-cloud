package ca.johnholloway.tacocloud.component;

import ca.johnholloway.tacocloud.model.Ingredient;
import ca.johnholloway.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;

    }

    @Override
    public Ingredient convert(String id){
        return ingredientRepository.findById(id).orElse(null);
    }
}
