package com.example.samochody.controllers;

import com.example.samochody.commands.EngineTypeCommand;
import com.example.samochody.converters.EngineTypeCommandToEngineType;
import com.example.samochody.model.EngineType;
import com.example.samochody.repositories.CarModelRepository;
import com.example.samochody.repositories.EngineTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EngineTypeController {

    private EngineTypeRepository engineTypeRepository;
    private CarModelRepository carModelRepository;
    private EngineTypeCommandToEngineType engineTypeCommandToEngineType;

    public EngineTypeController(EngineTypeRepository engineTypeRepository, CarModelRepository carModelRepository, EngineTypeCommandToEngineType engineTypeCommandToEngineType) {
        this.engineTypeRepository = engineTypeRepository;
        this.carModelRepository = carModelRepository;
        this.engineTypeCommandToEngineType = engineTypeCommandToEngineType;
    }

    @RequestMapping("/engineType/new")
    public String newCarModel(Model model) {
        model.addAttribute("engineType", new EngineTypeCommand());
        model.addAttribute("carModels", carModelRepository.findAll());
        return "engineType/addedit";
    }

    @RequestMapping("engineType/{id}/delete")
    public String deleteEngineType(@PathVariable("id") Long id) {
        engineTypeRepository.deleteById(id);
        return "redirect:/engineTypes";
    }

    @RequestMapping(value = {"/engineTypes", "engineType/list"})
    public String getEngineTypes(Model model) {
        model.addAttribute("engineTypes", engineTypeRepository.findAll());
        return "engineType/list";
    }

    @RequestMapping("/engineType/{id}/show")
    public String getEngineTypeDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("engineType", engineTypeRepository.findById(id).get());
        return "engineType/show";
    }

    @PostMapping("engineType")
    public String saveOrUpdate(@ModelAttribute EngineTypeCommand command) {
        EngineType detachedEngineType = engineTypeCommandToEngineType.convert(command);
        EngineType savedEngineType = engineTypeRepository.save(detachedEngineType);
        return "redirect:/engineType/" + savedEngineType.getId() + "/show";
    }
}
