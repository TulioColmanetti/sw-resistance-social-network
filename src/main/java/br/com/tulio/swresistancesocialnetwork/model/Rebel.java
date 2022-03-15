package br.com.tulio.swresistancesocialnetwork.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@Data
@Entity
public class Rebel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private UUID rebelId;
    @NotNull @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull @NotBlank(message = "Age is mandatory")
    private Integer age;
    @NotNull @NotBlank(message = "Gender is mandatory")
    private String gender;

}
