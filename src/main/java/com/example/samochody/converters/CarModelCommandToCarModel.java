package com.example.samochody.converters;

import com.example.samochody.commands.CarModelCommand;
import com.example.samochody.model.Brand;
import com.example.samochody.model.CarModel;
import com.example.samochody.model.Concern;
import com.example.samochody.repositories.BrandRepository;
import com.example.samochody.repositories.ConcernRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Component
public class CarModelCommandToCarModel implements Converter<CarModelCommand, CarModel> {

    private ConcernRepository concernRepository;
    private BrandRepository brandRepository;

    public CarModelCommandToCarModel(ConcernRepository concernRepository, BrandRepository brandRepository) {
        this.concernRepository = concernRepository;
        this.brandRepository = brandRepository;

    }

    @Synchronized
    @Nullable
    @Override
    public CarModel convert(CarModelCommand source) {
        if(source == null) {
            return null;
        }

        final CarModel carModel = new CarModel();
        carModel.setId(source.getId());
        carModel.setModelName(source.getModelName());
        carModel.setYearProduced(source.getYearProduced());
        carModel.setGeneration(source.getGeneration());

        Optional<Concern> concern = concernRepository.findById(source.getConcernId());

        if(concern.isPresent()) {
            carModel.setConcern(concern.get());
        } else {
            carModel.setConcern(concernRepository.getConcernByName("Unknown").get());
        }

        Optional<Brand> brand = brandRepository.findById(source.getBrandId());

        if(brand.isPresent()) {
            carModel.getBrands().add(brand.get());
        }

        return carModel;
    }
}
