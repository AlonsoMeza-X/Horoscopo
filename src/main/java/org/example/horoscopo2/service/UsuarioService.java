package org.example.horoscopo2.service;

import org.example.horoscopo2.dto.UsuarioCreateDto;
import org.example.horoscopo2.dto.UsuarioResponseDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
boolean registarUsuario(UsuarioCreateDto usuario, String confirmPassword);
boolean autentificarUsuario(String username, String password);
Optional<UsuarioResponseDto> findUsuarioByUsername(String username);
List<UsuarioResponseDto> findAll();
public void eliminarUsuario(int id);
public void update(UsuarioCreateDto usuario);
}
