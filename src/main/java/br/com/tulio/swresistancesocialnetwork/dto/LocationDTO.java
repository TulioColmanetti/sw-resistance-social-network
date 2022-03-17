package br.com.tulio.swresistancesocialnetwork.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1000.00")
    @NotNull
    private Double latitude;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1000.00")
    @NotNull
    private Double longitude;

    @NotNull
    @NotBlank(message = "Base name is mandatory")
    private String baseName;

}
