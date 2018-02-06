package me.dtavan.pokedex.services;

import me.dtavan.pokedex.domain.Pokemon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonServiceTest {

    @Autowired
    private JsonService jsonService;

    @Test
    public void parseJson() throws IOException {
        List<Pokemon> listParsed = jsonService.parseJson();

        assertThat(listParsed).isNotEmpty();
        assertThat(listParsed.size()).isEqualTo(386);
        assertThat(listParsed.get(0).getId()).isEqualTo(Integer.toString(1));

    }
}
