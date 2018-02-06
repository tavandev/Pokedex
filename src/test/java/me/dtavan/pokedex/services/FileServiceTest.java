package me.dtavan.pokedex.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {


    @Autowired
    private FileService fileService;

    @Test
    public void testFilesExists() throws IOException {
        File file = new ClassPathResource("/pkmn.json").getFile();
        assertThat(file).isNotNull();
        assertThat(file.getName()).isEqualTo("pkmn.json");
    }

    @Test
    public void fluxLisible() throws IOException {
        InputStream stream = new ClassPathResource("/pkmn.json").getInputStream();

        assertThat(stream).isNotNull();
    }

    @Test(expected = IOException.class)
    public void testFileNotExistsThrowsException() throws IOException {
        File file = new ClassPathResource("/pkmn2.json").getFile();
    }

    @Test
    public void testFileReturnString() {
        String file = fileService.loadJson("pkmn.json");

        assertThat(file.length()).isGreaterThan(0);
        assertThat(file).contains("Bulbizarre");
    }
}
