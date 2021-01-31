package com.example.samochody.converters;

import com.example.samochody.commands.BrandCommand;
import com.example.samochody.model.Brand;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.Nullable;

@Component
public class BrandCommandToBrand implements Converter<BrandCommand, Brand> {

    @Synchronized
    @Nullable
    @Override
    public Brand convert(BrandCommand source) {
        if(source == null) {
            return null;
        }

        final Brand brand = new Brand();
        brand.setBrandName(source.getBrandName());
        brand.setBrandLevel(source.getBrandLevel());

        return brand;
    }
}
