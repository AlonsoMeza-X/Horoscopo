package org.example.horoscopo2.mapper;

import org.example.horoscopo2.dto.UsuarioCreateDto;
import org.example.horoscopo2.dto.UsuarioResponseDto;
import org.example.horoscopo2.model.Usuario;

import java.sql.Timestamp;

public class UsaurioMapper {

    public static UsuarioResponseDto tDto(Usuario usuario) {
        return new UsuarioResponseDto(
          usuario.getNombre(),
          usuario.getUsername(),
          usuario.getEmail(),
          usuario.getFechaNacimiento(),
          usuario.getAnimal()
        );
    }

    public static Usuario toEntity(UsuarioCreateDto dto){
        return new Usuario(
                dto.getId(),
                dto.getNombre(),
                dto.getUsername(),
                dto.getEmail(),
                (Timestamp) dto.getFechaNacimiento(),
                dto.getPassword(),
                dto.getAnimal()
        );
    }

}
