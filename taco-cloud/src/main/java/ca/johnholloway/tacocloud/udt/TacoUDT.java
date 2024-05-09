package ca.johnholloway.tacocloud.udt;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@UserDefinedType("taco")
public class TacoUDT {

    private final UUID id;
    private final String name;
    private final Date createdAt;
    private final List<IngredientUDT> ingredients;

}
