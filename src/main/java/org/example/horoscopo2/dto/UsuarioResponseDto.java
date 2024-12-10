package org.example.horoscopo2.dto;

import java.util.Date;

public class UsuarioResponseDto {

    private String nombre;
    private String username;
    private String email;
    private Date fechaNacimiento;
    private int animal;

    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(String nombre, String username, String email, Date fechaNacimiento, int animal) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.animal = animal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "UsuarioResponseDto{" +
                "nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", animal=" + animal +
                '}';
    }
}
