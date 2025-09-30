package com.devdouglasm.agendadortarefas.business.mapper;

import com.devdouglasm.agendadortarefas.dto.TarefaDTO;
import com.devdouglasm.agendadortarefas.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(source = "id", target = "id")
    Tarefa paraTarefa(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(Tarefa tarefa);

    List<Tarefa> paraListaTarefa(List<TarefaDTO> dtos);

    List<TarefaDTO> paraListaTarefaDTO(List<Tarefa> tarefaList);
}
