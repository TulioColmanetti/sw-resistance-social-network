package br.com.tulio.swresistancesocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeItemsDTO {

    @NotNull
    @Min(0)
    private Long firstRebelId;

    @Valid
    @NotNull
    @NotEmpty
    private List<ItemDTO> firstRebelItems;

    @NotNull
    @Min(0)
    private Long secondRebelId;

    @Valid
    @NotNull
    @NotEmpty
    private List<ItemDTO> secondRebelItems;

}
