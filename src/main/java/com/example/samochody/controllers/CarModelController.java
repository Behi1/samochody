package com.example.samochody.controllers;

import com.example.samochody.commands.CarModelCommand;
import com.example.samochody.converters.CarModelCommandToCarModel;
import com.example.samochody.model.Brand;
import com.example.samochody.model.CarModel;
import com.example.samochody.repositories.BrandRepository;
import com.example.samochody.repositories.CarModelRepository;
import com.example.samochody.repositories.ConcernRepository;
import com.example.samochody.repositories.EngineTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CarModelController {

    private CarModelRepository carModelRepository;
    private CarModelCommandToCarModel carModelCommandToCarModel;
    private ConcernRepository concernRepository;
    private BrandRepository brandRepository;
    private EngineTypeRepository engineTypeRepository;

    public CarModelController(CarModelRepository carModelRepository, CarModelCommandToCarModel carModelCommandToCarModel, ConcernRepository concernRepository, BrandRepository brandRepository, EngineTypeRepository engineTypeRepository) {
        this.carModelRepository = carModelRepository;
        this.carModelCommandToCarModel = carModelCommandToCarModel;
        this.concernRepository = concernRepository;
        this.brandRepository = brandRepository;
        this.engineTypeRepository = engineTypeRepository;
    }

    @RequestMapping("/carModel/new")
    public String newCarModel(Model model) {
        model.addAttribute("carModel", new CarModelCommand());
        model.addAttribute("concerns", concernRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "carModel/addedit";
    }

    @RequestMapping("/carModel/{id}/delete")
    public String deleteCarModel(@PathVariable("id") Long id) {
        carModelRepository.deleteById(id);
        return "redirect:/carModels";
    }

    @RequestMapping(value = {"/carModels", "carModel/list"})
    public String getCarModels(Model model) {
        model.addAttribute("carModels", carModelRepository.findAll());
        return "carModel/list";
    }

    @RequestMapping("/carModel/{id}/show")
    public String getCarModelDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("carModel", carModelRepository.findById(id).get());
        return "carModel/show";
    }

    @RequestMapping("/carModel/{id}/engineTypes")
    public String getCarModelEngineTypes(Model model, @PathVariable("id") Long id) {
        Optional<CarModel>carModel = carModelRepository.findById(id);

        if(carModel.isPresent()) {
            model.addAttribute("engineTypes", engineTypeRepository.getAllByCarModelsIsContaining(carModel.get()));
            model.addAttribute("filter", "carModel: " + carModel.get().getModelName());
        } else {
            model.addAttribute("engineTypes", new ArrayList<>());
            model.addAttribute("filter", "this carModel doesn't exists.");
        }
        return "engineType/list";
    }

    @PostMapping("carModel")
    public String saveOrUpdate(@ModelAttribute CarModelCommand command) {
        CarModel detachedCarModel = carModelCommandToCarModel.convert(command);
        CarModel savedCarModel = carModelRepository.save(detachedCarModel);
        return "redirect:/carModel/" + savedCarModel.getId() + "/show";
    }

}
