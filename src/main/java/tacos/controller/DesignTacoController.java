package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.entity.Taco;
import tacos.repository.TacoRepository;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/design",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

//    @GetMapping(value = "/tacos/recent", produces = "application/hal+json")
//    public Resources<TacoResource> recentTaco() {
//        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//        List<Taco> tacoList = tacoRepository.findAll(pageRequest).getContent();
//        List<TacoResource> tacoResources = new TacoResourceAssembler().toResourceList(tacoList);
//        Resources<TacoResource> recentResources = new Resources<>(tacoResources);
//        recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTaco()).withRel("recent_tacos"));
//        return recentResources;
//    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @GetMapping("/{id}")
    public Taco tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        return optTaco.orElse(null);
    }
}
