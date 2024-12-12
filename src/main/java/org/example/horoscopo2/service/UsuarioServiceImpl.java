package org.example.horoscopo2.service;

import org.example.horoscopo2.dto.UsuarioCreateDto;
import org.example.horoscopo2.dto.UsuarioResponseDto;
import org.example.horoscopo2.mapper.UsaurioMapper;
import org.example.horoscopo2.repository.HoroscopoRepositoryImpl;
import org.example.horoscopo2.repository.UsuarioRepository;
import org.example.horoscopo2.repository.UsuarioRepositoryImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final HoroscopoRepositoryImpl horoscopoRepository;

    public UsuarioServiceImpl() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
        this.horoscopoRepository = new HoroscopoRepositoryImpl();
    }

    @Override
    public boolean registarUsuario(UsuarioCreateDto usuario, String confirmPassword) {
        if (!validarDatosRegistro(usuario, confirmPassword)) {
            System.out.println("Datos invalidos");
            return false;
        }

        try {
            queAnimal(usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            System.out.printf("Usuario ya existe");
            return false;
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            System.out.println("Email ya existe");
            return false;
        }

        return usuarioRepository.save(UsaurioMapper.toEntity(usuario));
        //Retorna un dinosaurio
    }

    @Override
    public boolean autentificarUsuario(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        return usuarioRepository.findByUsername(username)
                .map(usuario -> usuario.getPassword().equals(password)).orElse(false);

    }

    @Override
    public Optional<UsuarioResponseDto> findUsuarioByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();
        }

        return usuarioRepository.findByUsername(username)
                .map(UsaurioMapper::tDto);
    }

    @Override
    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsaurioMapper::tDto)
                .collect(Collectors.toList());
    }

    private boolean validarDatosRegistro(UsuarioCreateDto usuario, String confirmPassword) {
        Date date = new Date();

        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            return false;
        }

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            return false;
        }

        if (usuario.getFechaNacimiento() == null || usuario.getFechaNacimiento().after(date)) {
            return false;
        }

        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            return false;
        }

        if (!usuario.getPassword().equals(confirmPassword)) {
            return false;
        }
        return true;
    }

    public void queAnimal(UsuarioCreateDto usuario) {

        usuario.setAnimal(horoscopoRepository.getAnimalByFechaDeNacimiento(usuario.getFechaNacimiento()));

    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioRepository.delete(id);
    }
}
