package br.com.tulio.swresistancesocialnetwork.services;

import br.com.tulio.swresistancesocialnetwork.builder.RebelDTOBuilder;
import br.com.tulio.swresistancesocialnetwork.dto.RebelDTO;
import br.com.tulio.swresistancesocialnetwork.mapper.RebelMapper;
import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import br.com.tulio.swresistancesocialnetwork.repository.RebelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RebelServiceTest {

    @Mock
    RebelRepository rebelRepository;

    private RebelMapper rebelMapper = RebelMapper.INSTANCE;

    @InjectMocks
    RebelService rebelService;

    @Test
    void whenRebelInformedThenItShouldBeCreated() {
//        given
        RebelDTO expectedRebelDTO = RebelDTOBuilder.builder().build().toRebelDTO();
        Rebel expectedSavedRebel = rebelMapper.toModel(expectedRebelDTO);

//        when
        when(rebelRepository.save(expectedSavedRebel)).thenReturn(expectedSavedRebel);

//        then
        RebelDTO createdRebelDTO = rebelService.createRebel(expectedRebelDTO);

        assertThat(createdRebelDTO.getId(), is(equalTo(expectedRebelDTO.getId())));
        assertThat(createdRebelDTO.getName(), is(equalTo(expectedRebelDTO.getName())));
        assertThat(createdRebelDTO.getAge(), is(equalTo(expectedRebelDTO.getAge())));
    }

}