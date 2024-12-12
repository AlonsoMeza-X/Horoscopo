package org.example.horoscopo2.repository;

import org.example.horoscopo2.configuration.DatabaseConnection;
import org.example.horoscopo2.model.Horoscopo;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class HoroscopoRepositoryImpl implements HoroscopoRepository {
    private final DatabaseConnection databaseConnection;

    public HoroscopoRepositoryImpl() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    @Override
    public String getAnimalByFechaDeNacimiento(Date fecha) {
        String query = "SELECT animal FROM horoscopo WHERE ? BETWEEN fecha_inicio AND fecha_fin";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, (java.sql.Date) fecha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("animal");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
