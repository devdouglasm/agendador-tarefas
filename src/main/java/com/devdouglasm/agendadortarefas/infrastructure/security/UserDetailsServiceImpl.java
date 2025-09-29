package com.devdouglasm.agendadortarefas.infrastructure.security;

import com.devdouglasm.agendadortarefas.dto.UsuarioDTO;
import com.devdouglasm.agendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UsuarioClient usuarioClient;

    public UserDetails loadUserByUsername(String email, String token) {

        UsuarioDTO usuarioDTO = new UsuarioDTO(email, token);
        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
