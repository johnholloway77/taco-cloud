package ca.johnholloway.tacocloud.controller;

import ca.johnholloway.tacocloud.model.Taco;
import ca.johnholloway.tacocloud.repository.TacoRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://localhost:*")
public class TacoController {

    private final TacoRepository tacoRepository;
    public TacoController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @Value("${taco.orders.page-size}")
    private int pageSize;

    @GetMapping
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0, pageSize, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> optionalTaco = tacoRepository.findById(id);

        if(optionalTaco.isPresent()){
            return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        System.out.println("postTaco called!");
        return tacoRepository.save(taco);
    }




}
