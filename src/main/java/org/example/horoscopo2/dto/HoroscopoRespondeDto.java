package org.example.horoscopo2.dto;

public class HoroscopoRespondeDto {
    private int id;
    private String animal;

    public HoroscopoRespondeDto() {
    }

    public HoroscopoRespondeDto(int id, String animal) {
        this.animal = animal;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "HoroscopoRespondeDto{" +
                "id=" + id +
                ", animal='" + animal + '\'' +
                '}';
    }
}
