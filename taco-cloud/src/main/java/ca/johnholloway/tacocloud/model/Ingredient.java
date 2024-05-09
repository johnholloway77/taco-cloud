package ca.johnholloway.tacocloud.model;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@Document(collection = "ingredient")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force = true)
public class Ingredient {

    @MongoId
    private String id;

    private String name;
    private Type type;


    public enum Type{
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
