package me.dtavan.pokedex.bootstrap;


import me.dtavan.pokedex.domain.Pokemon;
import reactor.core.publisher.Flux;

import java.util.UUID;

public class ResourcesForTest {

    public final static String JSONPOKEMON = "[\n" +
            "  {\n" +
            "    \"index\": 1,\n" +
            "    \"name\": \"Bulbizarre\",\n" +
            "    \"categoryName\": \"Graine\",\n" +
            "    \"description\": \"Bulbizarre passe son temps à faire la sieste sous le soleil. Il absorbe les rayons de soleil pour faire doucement pousser la graine qu'il a sur son dos.\",\n" +
            "    \"hp\": 45,\n" +
            "    \"attack\": 49,\n" +
            "    \"defense\": 49,\n" +
            "    \"speed\": 45,\n" +
            "    \"attacksp\": 65,\n" +
            "    \"defensesp\": 65,\n" +
            "    \"type1\": \"Plante\",\n" +
            "    \"type2\": \"Poison\",\n" +
            "    \"gender\": \"Mixte\",\n" +
            "    \"eggGroup1\": \"Monstrueux\",\n" +
            "    \"eggGroup2\": \"Végétal\",\n" +
            "    \"ability1\": \"Engrais\",\n" +
            "    \"ability2\": \"Chlorophyle\",\n" +
            "    \"evolution\": [\n" +
            "      {\n" +
            "        \"method\": \"Par montée de niveau\",\n" +
            "        \"parameter\": \"16\",\n" +
            "        \"destination\": 2\n" +
            "      }\n" +
            "    ],\n" +
            "    \"frontImage\": \"\",\n" +
            "    \"backImage\": \"\",\n" +
            "    \"frontImageShiny\": \"\",\n" +
            "    \"backImageShiny\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"index\": 2,\n" +
            "    \"name\": \"Herbizarre\",\n" +
            "    \"categoryName\": \"Graine\",\n" +
            "    \"description\": \"Herbizarre a les pattes musclées afin de supporter le poids du bourgeon qu'il a sur le dos. S'il se prélasse au soleil, son bourgeon va donner naissance à une fleur.\",\n" +
            "    \"hp\": 60,\n" +
            "    \"attack\": 62,\n" +
            "    \"defense\": 63,\n" +
            "    \"speed\": 60,\n" +
            "    \"attacksp\": 80,\n" +
            "    \"defensesp\": 80,\n" +
            "    \"type1\": \"Plante\",\n" +
            "    \"type2\": \"Poison\",\n" +
            "    \"gender\": \"Mixte\",\n" +
            "    \"eggGroup1\": \"Monstrueux\",\n" +
            "    \"eggGroup2\": \"Végétal\",\n" +
            "    \"ability1\": \"Engrais\",\n" +
            "    \"ability2\": \"Chlorophyle\",\n" +
            "    \"evolution\": [\n" +
            "      {\n" +
            "        \"method\": \"Par montée de niveau\",\n" +
            "        \"parameter\": \"32\",\n" +
            "        \"destination\": 3\n" +
            "      }\n" +
            "    ],\n" +
            "    \"frontImage\": \"\",\n" +
            "    \"backImage\": \"\",\n" +
            "    \"frontImageShiny\": \"\",\n" +
            "    \"backImageShiny\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"index\": 3,\n" +
            "    \"name\": \"Florizarre\",\n" +
            "    \"categoryName\": \"Graine\",\n" +
            "    \"description\": \"Une belle fleur se trouve sur son dos. Elle prend une couleur vive lorsqu'elle est bien nourrie et bien ensoleillée. Le parfum de cette fleur peut apaiser les gens.\",\n" +
            "    \"hp\": 80,\n" +
            "    \"attack\": 82,\n" +
            "    \"defense\": 83,\n" +
            "    \"speed\": 80,\n" +
            "    \"attacksp\": 100,\n" +
            "    \"defensesp\": 100,\n" +
            "    \"type1\": \"Plante\",\n" +
            "    \"type2\": \"Poison\",\n" +
            "    \"gender\": \"Mixte\",\n" +
            "    \"eggGroup1\": \"Monstrueux\",\n" +
            "    \"eggGroup2\": \"Végétal\",\n" +
            "    \"ability1\": \"Engrais\",\n" +
            "    \"ability2\": \"Chlorophyle\",\n" +
            "    \"evolution\": null,\n" +
            "    \"frontImage\": \"\",\n" +
            "    \"backImage\": \"\",\n" +
            "    \"frontImageShiny\": \"\",\n" +
            "    \"backImageShiny\": \"\"\n" +
            "  }\n" +
            "]";

    public static final Pokemon bulbizarre = new Pokemon(UUID.randomUUID().toString(), "Bulbizarre", "Graine",
            "Bulbizarre passe son temps à faire la sieste sous le soleil. Il absorbe les rayons de soleil pour faire doucement pousser la graine qu'il a sur son dos.",
            45, 49, 49, 45, 65, 65,
            "Plante", "Poison", "Mixte",
            "Monstrueux", "Végétal",
            "Engrais", "Chlorophyle",
            "", "", "", "");

    public static final Pokemon herbizarre = new Pokemon(UUID.randomUUID().toString(), "Herbizarre", "Graine",
            "Herbizarre a les pattes musclées afin de supporter le poids du bourgeon qu'il a sur le dos. S'il se prélasse au soleil, son bourgeon va donner naissance à une fleur.",
            60, 62, 63, 60, 80, 80,
            "Plante", "Poison", "Mixte",
            "Monstrueux", "Végétal",
            "Engrais", "Chlorophyle",
            "", "", "", "");

    public static final Pokemon florizarre =
            new Pokemon(UUID.randomUUID().toString(), "Florizarre", "Graine",
                    "Une belle fleur se trouve sur son dos. Elle prend une couleur vive lorsqu'elle est bien nourrie et bien ensoleillée. Le parfum de cette fleur peut apaiser les gens.",
                    80, 82, 83, 80, 100, 100,
                    "Plante", "Poison", "Mixte",
                    "Monstrueux", "Végétal",
                    "Engrais", "Chlorophyle",
                    "", "", "", "");

    public static Flux<Pokemon> fluxPokemons = Flux.just(bulbizarre, herbizarre, florizarre);
}
