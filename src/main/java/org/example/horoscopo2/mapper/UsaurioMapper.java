package org.example.horoscopo2.mapper;

import org.example.horoscopo2.dto.UsuarioCreateDto;
import org.example.horoscopo2.dto.UsuarioResponseDto;
import org.example.horoscopo2.model.Usuario;

public class UsaurioMapper {

    public static UsuarioResponseDto tDto(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getFechaNacimiento(),
                usuario.getAnimal()
        );
    }

    public static Usuario toEntity(UsuarioCreateDto dto) {
        return new Usuario(
                dto.getId(),
                dto.getNombre(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getFechaNacimiento(),
                dto.getPassword(),
                dto.getAnimal()
        );
    }
}
