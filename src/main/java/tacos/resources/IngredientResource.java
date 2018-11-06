package tacos.resources;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import tacos.entity.Ingredient;

public class IngredientResource extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private Ingredient.Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
