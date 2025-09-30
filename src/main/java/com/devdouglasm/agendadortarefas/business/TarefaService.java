package com.devdouglasm.agendadortarefas.business;

import com.devdouglasm.agendadortarefas.business.mapper.TarefaMapper;
import com.devdouglasm.agendadortarefas.business.mapper.TarefaUpdateMapper;
import com.devdouglasm.agendadortarefas.dto.TarefaDTO;
import com.devdouglasm.agendadortarefas.enums.StatusNotificacaoEnum;
import com.devdouglasm.agendadortarefas.infrastructure.entity.Tarefa;
import com.devdouglasm.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.devdouglasm.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.devdouglasm.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateMapper tarefaUpdateMapper;

    public TarefaDTO salvarTarefa(TarefaDTO dto, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        Tarefa tarefa = tarefaMapper.paraTarefa(dto);
        return tarefaMapper.paraTarefaDTO(tarefaRepository.save(tarefa));
    }

    public List<TarefaDTO> buscaTarefaPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        List<Tarefa> tarefaList = tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal);
        return tarefaMapper.paraListaTarefaDTO(tarefaList);
    }

    public List<TarefaDTO> buscaTarefaPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<Tarefa> tarefaList = tarefaRepository.findByEmailUsuario(email);
        return tarefaMapper.paraListaTarefaDTO(tarefaList);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletarTarefaPorId(String id) {

        if (!tarefaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado");
        }
        try {
            tarefaRepository.deleteById(id);
        }
        catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado");
        }
    }

    public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            Tarefa entity = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Id não encontrado " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefaDTO updateTarefa(TarefaDTO dto, String id) {
        try {
            Tarefa entity = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Id não encontrado " + id));
            tarefaUpdateMapper.updateTarefas(dto, entity);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar a tarefa " + e.getCause());
        }
    }
}
