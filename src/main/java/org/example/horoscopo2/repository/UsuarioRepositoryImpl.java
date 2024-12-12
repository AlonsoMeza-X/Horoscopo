package org.example.horoscopo2.repository;

import com.mysql.cj.Session;
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
        String query = "INSERT INTO usuarios (id, nombre, username, email, fecha_nacimiento, password, animal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getUsername());
            stmt.setString(4, usuario.getEmail());
            stmt.setDate(5, usuario.getFechaNacimiento());
            stmt.setString(6, usuario.getPassword());
            stmt.setString(7, usuario.getAnimal());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        String query = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuarios WHERE username = ?";
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
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")
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
        String query = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuarios WHERE email = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return Optional.of(new Usuario(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")));
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Usuario> findById(int id) {
        String query = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuarios WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return Optional.of(new Usuario(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM usuario WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(query)
        ) {
            pstm.setInt(1, id);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("No es posible eliminar usuario", e);
        }
    }

    @Override
    public void edit(Usuario usuario) {
        String query = "UPDATE usuarios SET nombre = ?, username = ?, email = ?, fecha_nacimiento = ?, password = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(query)
        ) {
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getUsername());
            pstm.setString(3, usuario.getEmail());
            pstm.setDate(4, usuario.getFechaNacimiento());
            pstm.setString(5, usuario.getPassword());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("No es posible editar usuario", e);
        }
    }
}
