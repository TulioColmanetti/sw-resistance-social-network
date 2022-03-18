package br.com.tulio.swresistancesocialnetwork.builder;

import br.com.tulio.swresistancesocialnetwork.dto.ItemDTO;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.enums.ItemType;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class RebelDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Luke Skywalker";

    @Builder.Default
    private Integer age = 32;

    @Builder.Default
    private String gender = "Male";

    @Builder.Default
    private Double latitude = 500.0;

    @Builder.Default
    private Double longitude = 500.0;

    @Builder.Default
    private String baseName = "Tatooine Hidden Base";

    @Builder.Default
    private List<ItemDTO> items = new ArrayList<>() {{
        add(new ItemDTO(1L, ItemType.FOOD));
        add(new ItemDTO(2L, ItemType.WATER));
    }};

    public RebelDTO toRebelDTO(){
        return new RebelDTO(id,name,age,gender,latitude,longitude,baseName,items);
    }
}
