package com.devdouglasm.agendadortarefas.business.mapper;

import com.devdouglasm.agendadortarefas.dto.TarefaDTO;
import com.devdouglasm.agendadortarefas.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {

    void updateTarefas(TarefaDTO dto, @MappingTarget Tarefa tarefa);
}
