package br.com.tulio.swresistancesocialnetwork.mapper;

import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RebelMapper {
    RebelMapper INSTANCE = Mappers.getMapper(RebelMapper.class);

    Rebel toModel(RebelDTO rebelDTO);

    RebelDTO toDTO(Rebel rebel);
}
