package br.com.tulio.swresistancesocialnetwork.dto;

import br.com.tulio.swresistancesocialnetwork.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    @NotNull
    private ItemType itemType;

}
