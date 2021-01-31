package com.example.samochody.converters;

import com.example.samochody.commands.ConcernCommand;
import com.example.samochody.model.Concern;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.Nullable;

@Component
public class ConcernCommandToConcern implements Converter<ConcernCommand, Concern> {

    @Synchronized
    @Nullable
    @Override
    public Concern convert(ConcernCommand source) {
        if(source == null) {
            return null;
        }

        final Concern concern = new Concern();
        concern.setName(source.getName());
        concern.setYear(source.getYear());
        concern.setAddress(source.getAddress());

        return concern;
    }
}
