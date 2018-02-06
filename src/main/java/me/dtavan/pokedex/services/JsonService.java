package me.dtavan.pokedex.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.dtavan.pokedex.domain.Pokemon;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JsonService {

    private FileService service;

    public JsonService(FileService service) {
        this.service = service;
    }

    public List<Pokemon> parseJson() throws IOException {
        String strPokemonsJson = service.loadJson("pkmn.json");
        System.out.println(strPokemonsJson);
        List<Pokemon> listeToReturn;
        ObjectMapper mapper = new ObjectMapper();

        listeToReturn = Arrays.asList(mapper.readValue(strPokemonsJson, Pokemon[].class));

        return listeToReturn;
    }
}
