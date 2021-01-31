package com.example.samochody.tools;

import com.example.samochody.model.Brand;
import com.example.samochody.model.CarModel;
import com.example.samochody.model.Concern;
import com.example.samochody.model.EngineType;
import com.example.samochody.repositories.BrandRepository;
import com.example.samochody.repositories.CarModelRepository;
import com.example.samochody.repositories.ConcernRepository;
import com.example.samochody.repositories.EngineTypeRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    private ConcernRepository concernRepository;
    private BrandRepository brandRepository;
    private CarModelRepository carModelRepository;
    private EngineTypeRepository engineTypeRepository;

    public DBInflater(ConcernRepository concernRepository, BrandRepository brandRepository, CarModelRepository carModelRepository, EngineTypeRepository engineTypeRepository) {
        this.concernRepository = concernRepository;
        this.brandRepository = brandRepository;
        this.carModelRepository = carModelRepository;
        this.engineTypeRepository = engineTypeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Concern volkswagenGroup = new Concern("Volkswagen Group", "1937", "Wolfsburg, Germany");
        Brand volkswagen = new Brand("Volkswagen", "Mid-Tier");
        CarModel passat = new CarModel("Passat", "2001-2005","Generation B5 FL", volkswagenGroup);
        EngineType avf = new EngineType("AVF", "1.9L", "Diesel");
        volkswagen.getCarModels().add(passat);
        passat.getBrands().add(volkswagen);
        avf.getCarModels().add(passat);
        passat.getEngineTypes().add(avf);
        concernRepository.save(volkswagenGroup);
        brandRepository.save(volkswagen);
        carModelRepository.save(passat);
        engineTypeRepository.save(avf);

        Concern gmc = new Concern("GMC", "1911", "Detroit, Michigan, USA");
        Brand chevrolet = new Brand("Chevrolet", "Mid-Tier");
        CarModel camaro = new CarModel("Camaro", "2015-nowadays","Generation VI", gmc);
        EngineType ls9 = new EngineType("LS9", "7.0L", "Petrol");
        chevrolet.getCarModels().add(camaro);
        camaro.getBrands().add(chevrolet);
        ls9.getCarModels().add(camaro);
        camaro.getEngineTypes().add(ls9);
        concernRepository.save(gmc);
        brandRepository.save(chevrolet);
        carModelRepository.save(camaro);
        engineTypeRepository.save(ls9);

        Concern toyotaMotor = new Concern("Toyota Motor Corporation", "1937", "Toyota, Japan");
        Brand lexus = new Brand("Lexus", "High-Tier");
        CarModel is300 = new CarModel("IS300", "1998-2005","Generation IS I", toyotaMotor);
        EngineType jz2 = new EngineType("2JZ-GE R6", "3.0L", "Petrol");
        lexus.getCarModels().add(is300);
        is300.getBrands().add(lexus);
        jz2.getCarModels().add(is300);
        is300.getEngineTypes().add(jz2);
        concernRepository.save(toyotaMotor);
        brandRepository.save(lexus);
        carModelRepository.save(is300);
        engineTypeRepository.save(jz2);
    }
}
