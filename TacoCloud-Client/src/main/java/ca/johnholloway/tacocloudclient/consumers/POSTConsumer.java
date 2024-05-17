package ca.johnholloway.tacocloudclient.consumers;

import ca.johnholloway.tacocloudclient.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class POSTConsumer {

    private RestTemplate rest = new RestTemplate();

    public Ingredient createIngredient(Ingredient ingredient) {
        try {
            return rest.postForObject("http://localhost:8080/api-data/ingredients", ingredient, Ingredient.class);

        } catch(HttpClientErrorException ex){
            log.info(ex.getStatusCode().toString());
            return null;
        }
    }


}
