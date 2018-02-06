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
public class DataBaseServiceTest {

    @Autowired
    private DataBaseService service;


    @Test
    public void seedDataBase() throws IOException {
        List<Pokemon> pokemonList = service.seedDataBase();

        assertThat(pokemonList).isNotEmpty();
        assertThat(pokemonList.size()).isEqualTo(386);
    }
}
