package org.example.horoscopo2.mapper;

import org.example.horoscopo2.dto.HoroscopoCreateDto;
import org.example.horoscopo2.dto.HoroscopoRespondeDto;
import org.example.horoscopo2.model.Horoscopo;

public class HoroscopoMapper {
    public static HoroscopoRespondeDto toEntity(Horoscopo horoscopo) {
        return new HoroscopoRespondeDto(horoscopo.getId(), horoscopo.getAnimal());
    }

    public static HoroscopoCreateDto toDto(Horoscopo horoscopo) {
        return new HoroscopoCreateDto(horoscopo.getId(), horoscopo.getAnimal(), horoscopo.getFechaInicio(), horoscopo.getFechaFinal());
    }
}
