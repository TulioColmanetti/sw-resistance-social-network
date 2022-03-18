package br.com.tulio.swresistancesocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    @DecimalMin(value = "0.0")
    @NotNull
    private Double traitorsPercentage;

    @DecimalMin(value = "0.0")
    @NotNull
    private Double rebelsPercentage;

    @Valid
    @NotNull
    private List<Double> averageItemQuantityPerRebel;

    @DecimalMin(value = "0.0")
    @NotNull
    private Long itemPointsLostDueToTraitors;
}
