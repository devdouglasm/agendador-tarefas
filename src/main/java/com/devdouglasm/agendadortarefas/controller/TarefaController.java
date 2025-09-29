package com.devdouglasm.agendadortarefas.controller;

import com.devdouglasm.agendadortarefas.business.TarefaService;
import com.devdouglasm.agendadortarefas.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> salvarTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.salvarTarefa(dto, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorPeriodo(@RequestParam
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                     LocalDateTime dataInicial,
                                                                 @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                     LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefaPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }
}
