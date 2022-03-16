package br.com.tulio.swresistancesocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RebelDTO {

    private Long id;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(0)
    @NotNull
    private Integer age;

    @NotNull
    @NotBlank(message = "Gender is mandatory")
    private String gender;

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

    @Valid
    @NotEmpty
    private List<ItemDTO> items;
}
