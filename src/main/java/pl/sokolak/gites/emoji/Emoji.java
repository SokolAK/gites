package pl.sokolak.gites.emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sokolak.gites.tag.Tag;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Emoji {

    @Id
    private String name;
    private String hex;
    private String dec;
    private String image;

    @ManyToMany
    @JoinTable(name = "emoji_tag")
    @JsonIgnoreProperties("emojis")
    @OrderColumn
    private List<Tag> tags = new ArrayList<>();

    public Emoji(String name, String hex, String dec, String image) {
        this.name = name;
        this.hex = hex;
        this.dec = dec;
        this.image = image;
    }
}
