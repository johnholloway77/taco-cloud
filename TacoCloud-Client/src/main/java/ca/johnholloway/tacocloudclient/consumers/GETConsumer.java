package ca.johnholloway.tacocloudclient.consumers;

import ca.johnholloway.tacocloudclient.model.Ingredient;
import org.springframework.web.client.RestTemplate;

public  class GETConsumer {


    private RestTemplate rest = new RestTemplate();

    public Ingredient getIngredientById(String ingredientId){
        return rest.getForObject("http://localhost:8080/data-api/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

}
