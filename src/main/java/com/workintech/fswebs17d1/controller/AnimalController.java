package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path= "/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals = new HashMap<>();

    @PostConstruct
    public void loadAll(){
        System.out.println("postconstruct çalıştı.");
        this.animals= new HashMap<>();
        this.animals.put(1,new Animal(1,"maymun"));
    }

    @GetMapping
    public List<Animal> getAnimals(){
        System.out.println("------animals get all triggerred!");
        return animals.values().stream().toList();
    }

    @GetMapping("{id}")
    public Animal getAnimal(@PathVariable int id){
        if(id<0){
            System.out.println("id cannot be less then zero!! ID: "+id);
            return null;
        }
        return this.animals.get(id);
    }

    @PutMapping("{id}")
    public Animal updateAnimal(@PathVariable int id,@RequestBody Animal newAnimal){
        animals.put(id,newAnimal);
        return animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(),animal);
    }

    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable int id){
        Animal animal = animals.get(id);
        animals.remove(id);

    }


}
