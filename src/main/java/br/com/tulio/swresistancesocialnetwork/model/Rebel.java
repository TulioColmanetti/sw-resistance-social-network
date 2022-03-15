package br.com.tulio.swresistancesocialnetwork.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Rebel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private Integer age;
    @NotNull @NotEmpty
    private String gender;

}
