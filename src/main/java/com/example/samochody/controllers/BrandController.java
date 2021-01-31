package com.example.samochody.controllers;

import com.example.samochody.commands.BrandCommand;
import com.example.samochody.converters.BrandCommandToBrand;
import com.example.samochody.model.Brand;
import com.example.samochody.repositories.BrandRepository;
import com.example.samochody.repositories.CarModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BrandController {

    private BrandRepository brandRepository;
    private CarModelRepository carModelRepository;
    private BrandCommandToBrand brandCommandToBrand;

    public BrandController(BrandRepository brandRepository, CarModelRepository carModelRepository, BrandCommandToBrand brandCommandToBrand) {
        this.brandRepository = brandRepository;
        this.carModelRepository = carModelRepository;
        this.brandCommandToBrand = brandCommandToBrand;
    }

    @RequestMapping("/brand/new")
    public String newCarModel(Model model) {
        model.addAttribute("brand", new BrandCommand());
        return "brand/addedit";
    }

    @RequestMapping("/brand/{id}/delete")
    public String deleteBrand(@PathVariable("id") Long id) {
        brandRepository.deleteById(id);
        return "redirect:/brands";
    }

    @RequestMapping(value = {"/brands", "/brand/list"})
    public String getBrands(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        return "brand/list";
    }

    @RequestMapping("/brand/{id}/show")
    public String getBrandDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("brand", brandRepository.findById(id).get());
        return "brand/show";
    }

    @RequestMapping("/brand/{id}/carModels")
    public String getBrandCarModels(Model model, @PathVariable("id") Long id) {
        Optional<Brand> brand = brandRepository.findById(id);

        if (brand.isPresent()) {
            model.addAttribute("carModels", carModelRepository.getAllByBrandsIsContaining(brand.get()));
            model.addAttribute("filter", "brand: " + brand.get().getBrandName());
        } else {
            model.addAttribute("carModels", new ArrayList<>());
            model.addAttribute("filter", "this brand doesn't exists.");
        }

        return "carModel/list";
    }

    @PostMapping("brand")
    public String saveOrUpdate(@ModelAttribute BrandCommand command) {

        Optional<Brand> brandOptional = brandRepository.getFirstByBrandName(command.getBrandName());

        if(!brandOptional.isPresent()) {
            Brand detachedBrand = brandCommandToBrand.convert(command);
            Brand savedBrand = brandRepository.save(detachedBrand);
            return "redirect:/brand/" + savedBrand.getId() + "/show";
        } else {
            System.out.println("We don't have this brand yet");
            return "redirect:/brand/" + brandOptional.get().getId() + "/show";
        }
    }
}
