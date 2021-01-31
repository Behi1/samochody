package com.example.samochody.controllers;

import com.example.samochody.commands.ConcernCommand;
import com.example.samochody.converters.ConcernCommandToConcern;
import com.example.samochody.model.Concern;
import com.example.samochody.repositories.ConcernRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ConcernController {

    private ConcernRepository concernRepository;
    private ConcernCommandToConcern concernCommandToConcern;

    public ConcernController(ConcernRepository concernRepository, ConcernCommandToConcern concernCommandToConcern) {
        this.concernRepository = concernRepository;
        this.concernCommandToConcern = concernCommandToConcern;
    }

    @RequestMapping(value = {"/concerns", "/concern/list"})
    public String getConcerns(Model model) {
        model.addAttribute("concerns", concernRepository.findAll());
        return "concern/list";
    }

    @RequestMapping("/concern/new")
    public String newCarModel(Model model) {
        model.addAttribute("concern", new ConcernCommand());
        return "concern/addedit";
    }

    @RequestMapping("/concern/{id}/delete")
    public String deleteConcern(@PathVariable("id") Long id) {
        concernRepository.deleteById(id);
        return "redirect:/concerns";
    }

    @RequestMapping("/concern/{id}/show")
    public String getConcernDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("concern", concernRepository.findById(id).get());
        return "concern/show";
    }

    @PostMapping("concern")
    public String saveOrUpdate(@ModelAttribute ConcernCommand command) {

        Optional<Concern> concernOptional = concernRepository.getConcernByName(command.getName());

        if(!concernOptional.isPresent()) {
            Concern detachedConcern = concernCommandToConcern.convert(command);
            Concern savedConcern = concernRepository.save(detachedConcern);
            return "redirect:/concern/" + savedConcern.getId() + "/show";
        } else {
            System.out.println("We don't have this concern yet");
            return "redirect:/concern/" + concernOptional.get().getId() + "/show";
        }
    }
}
