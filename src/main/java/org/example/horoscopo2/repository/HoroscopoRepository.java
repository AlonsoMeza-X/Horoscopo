package org.example.horoscopo2.repository;

import org.example.horoscopo2.model.Horoscopo;

import java.util.Date;
import java.util.List;

public interface HoroscopoRepository {
    List<Horoscopo> findAllAnimal();

    int findAnimalByDate(Date fechaNacimiento);
}
