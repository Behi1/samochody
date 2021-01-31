package com.example.samochody.converters;

import com.example.samochody.commands.EngineTypeCommand;
import com.example.samochody.model.CarModel;
import com.example.samochody.model.EngineType;
import com.example.samochody.repositories.CarModelRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Component
public class EngineTypeCommandToEngineType implements Converter<EngineTypeCommand, EngineType> {

    private CarModelRepository carModelRepository;

    public EngineTypeCommandToEngineType(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public EngineType convert(EngineTypeCommand source) {
        if(source == null) {
            return null;
        }

        final EngineType engineType = new EngineType();
        engineType.setEngineName(source.getEngineName());
        engineType.setCapacity(source.getCapacity());
        engineType.setFuelType(source.getFuelType());

        Optional<CarModel> carModel = carModelRepository.findById(source.getCarModelId());

        if(carModel.isPresent()) {
            engineType.getCarModels().add(carModel.get());
        }

        return engineType;
    }
}
