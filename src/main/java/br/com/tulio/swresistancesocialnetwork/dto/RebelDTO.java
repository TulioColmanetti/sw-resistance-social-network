package br.com.tulio.swresistancesocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
