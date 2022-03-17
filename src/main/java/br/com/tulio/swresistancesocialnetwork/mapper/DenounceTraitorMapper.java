package br.com.tulio.swresistancesocialnetwork.mapper;

import br.com.tulio.swresistancesocialnetwork.dto.DenounceTraitorDTO;
import br.com.tulio.swresistancesocialnetwork.model.DenounceTraitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DenounceTraitorMapper {
    DenounceTraitorMapper INSTANCE = Mappers.getMapper(DenounceTraitorMapper.class);

    DenounceTraitor toModel(DenounceTraitorDTO denounceTraitorDTO);

    DenounceTraitorDTO toDTO(DenounceTraitor denounceTraitor);

}
