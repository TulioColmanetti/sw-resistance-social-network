package br.com.tulio.swresistancesocialnetwork.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DenounceTraitorDTO {

    @Min(1)
    @NotNull
    Long informerId;

    @Min(1)
    @NotNull
    Long traitorId;
}
