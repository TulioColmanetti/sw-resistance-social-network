package br.com.tulio.swresistancesocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Double traitorsPercentage;
    private Double rebelsPercentage;
    private List<Double> averageItemQuantityPerRebel;
    private Long itemPointsLostDueToTraitors;
}
