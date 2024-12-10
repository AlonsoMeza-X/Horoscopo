package org.example.horoscopo2.repository;

import org.example.horoscopo2.configuration.DatabaseConnection;
import org.example.horoscopo2.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final DatabaseConnection databaseConnection;

    public UsuarioRepositoryImpl() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    @Override
    public boolean save(Usuario usuario) {
        String query = "INSERT INSERT INTO usuarios (id, nombre, username, email, fechaNacimiento, password, animal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getUsername());
            stmt.setString(4, usuario.getEmail());
            stmt.setTimestamp(5, usuario.getFechaNacimiento());
            stmt.setString(6, usuario.getPassword());
            stmt.setInt(7, usuario.getAnimal());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        String query = "SELECT id, nombre, email, fechaNacimiento, password, animal FROM usuarios WHERE username = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getTimestamp("fechaNacimiento"),
                        rs.getString("password"),
                        rs.getInt("animal")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String query = "SELECT id, nombre, username, email, fechaNacimiento, password animal FROM usuarios WHERE username = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return Optional.of(new Usuario(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getTimestamp("fechaNacimiento"),
                        rs.getString("password"),
                        rs.getInt("animal")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT id, nombre, username, email, fechaNacimiento, password animal FROM usuarios WHERE username = ?";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getTimestamp("fechaNacimiento"),
                        rs.getString("password"),
                        rs.getInt("animal")));
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
