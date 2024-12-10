package org.example.horoscopo2.repository;

import org.example.horoscopo2.configuration.DatabaseConnection;
import org.example.horoscopo2.model.Horoscopo;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HoroscopoRepositoryImpl implements HoroscopoRepository {
    private final DatabaseConnection databaseConnection;

    public HoroscopoRepositoryImpl() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }


    @Override
    public List<Horoscopo> findAllAnimal() {
        String query = "SELECT DISTINCT animal FROM horoscopo ORDER BY animal";
        List<Horoscopo> horoscopos = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                horoscopos.add(
                        new Horoscopo(
                                rs.getString("animal"),
                                null,
                                null)
                );
            }
            return horoscopos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

        @Override
    public int findAnimalByDate(Date fechaNacimiento) {
        String query = "SELECT id FROM horoscopo WHERE ? BETWEEN fecha_inicio AND fecha_fin";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            if (fechaNacimiento == null) {
                throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
            }

            stmt.setTimestamp(1, Timestamp.valueOf(String.valueOf(fechaNacimiento)));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Error: No se encontró un rango de fechas para el horóscopo");
        throw new IllegalStateException("No se encontró un rango de fechas válido para el horóscopo");
    }
}
