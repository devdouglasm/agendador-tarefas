package com.devdouglasm.agendadortarefas.infrastructure.client;

import com.devdouglasm.agendadortarefas.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/tarefa")
    UsuarioDTO achaUsuario(@RequestParam String email,
                           @RequestHeader("Authorization") String token);

}
