package br.com.tulio.swresistancesocialnetwork.mapper;

import br.com.tulio.swresistancesocialnetwork.dto.ItemDTO;
import br.com.tulio.swresistancesocialnetwork.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item toModel(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);
}
