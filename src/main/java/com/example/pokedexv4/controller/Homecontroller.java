package com.example.pokedexv4.controller;

import com.example.pokedexv4.model.Pokemon;
import com.example.pokedexv4.repository.Repo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Homecontroller {

    Repo repo;
    Homecontroller(){
        repo = new Repo();
    }
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pokemons", repo.selectAll());
        return "index";
    }
    @GetMapping("/addPokemon")
    public String addPokemon(){
        return "add-pokemon";
    }
    @PostMapping("/addNewPokemon")
    public String addNewPokemon(@RequestParam("id") int id,@RequestParam("attack") int attack,@RequestParam("defence") int defence,@RequestParam("hp") int hp,@RequestParam("name") String name,@RequestParam("primary") String primary,@RequestParam("secondary") String secondary,@RequestParam("specialattack") int specialAttack, @RequestParam("specialdefence") int specialDefence,@RequestParam("speed") int speed){
        repo.insert(id,name,speed,specialDefence,specialAttack,defence,attack,hp,primary,secondary);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        repo.delete(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id,Model model){
        Pokemon pokemon = repo.findById(id);
        model.addAttribute("pokemon", pokemon);
        return "update";
    }

    @PostMapping("/update/updatedPokemon")
    public String updatedPokemon(@RequestParam("id") int id,@RequestParam("attack") int attack,@RequestParam("defence") int defence,@RequestParam("hp") int hp,@RequestParam("name") String name,@RequestParam("primary") String primary,@RequestParam("secondary") String secondary,@RequestParam("specialattack") int specialAttack, @RequestParam("specialdefence") int specialDefence,@RequestParam("speed") int speed){
        repo.update(id,name,speed,specialDefence,specialAttack,defence,attack,hp,primary,secondary);
        return "redirect:/";
    }

}
