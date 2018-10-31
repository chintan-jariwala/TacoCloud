package tacos.resources;

import org.springframework.hateoas.core.Relation;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tacos.controller.DesignTacoController;
import tacos.entity.Taco;

import java.util.List;
import java.util.stream.Collectors;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {


    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    protected TacoResource instantiateResource(Taco entity) {
        return new TacoResource(entity);
    }

    @Override
    public TacoResource toResource(Taco taco) {
        return createResourceWithId(taco.getId(), taco);
    }

    public List<TacoResource> toResourceList(List<Taco> tacoList) {
        return tacoList.stream().map(taco -> createResourceWithId(taco.getId(), taco))
                .collect(Collectors.toList());
    }
}
