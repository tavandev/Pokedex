package me.dtavan.pokedex.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Pokemon {
    @Id
    private String id;

    private String name;
    private String categoryName;
    private String description;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer speed;
    private Integer attacksp;
    private Integer defensesp;
    private String type1;
    private String type2;
    private String gender;
    private String eggGroup1;
    private String eggGroup2;
    private String ability1;
    private String ability2;


    //private List<Evolution> evolution;
    private String frontImage;
    private String backImage;
    private String frontImageShiny;
    private String backImageShiny;

}
