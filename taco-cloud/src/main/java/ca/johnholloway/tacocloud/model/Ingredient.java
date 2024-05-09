package ca.johnholloway.tacocloud.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force = true)
@Table("ingredients")
public class Ingredient {

    @PrimaryKey
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
