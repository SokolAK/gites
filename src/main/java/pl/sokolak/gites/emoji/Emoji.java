package pl.sokolak.gites.emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sokolak.gites.category.Category;
import pl.sokolak.gites.tag.Tag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Emoji {

    @Id
    private String id;
    private String hex;
    private String dec;
    private String image;

    @ManyToMany
    @JoinTable(name = "emoji_category")
    @JsonIgnoreProperties("emojis")
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "emoji_tag")
    @JsonIgnoreProperties("emojis")
    private List<Tag> tags = new ArrayList<>();

    public Emoji(String id, String hex, String dec, String image) {
        this.id = id;
        this.hex = hex;
        this.dec = dec;
        this.image = image;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }
}
