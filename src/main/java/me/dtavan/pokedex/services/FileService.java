package me.dtavan.pokedex.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class FileService {


//    public String downloadJson(String s) throws IOException {
//        String line;
//        URL url = new URL("https://raw.githubusercontent.com/davidtavan/misc/master/pkmn.json");
//        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//
//        StringBuilder builder = new StringBuilder();
//        InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//        while ((line = bufferedReader.readLine()) != null) {
//            builder.append(line);
//        }
//        return builder.toString();
//    }

    public String loadJson(String file) {
        String line;
        StringBuilder builder = new StringBuilder();
        try {

            InputStream stream = new ClassPathResource("/" + file).getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
