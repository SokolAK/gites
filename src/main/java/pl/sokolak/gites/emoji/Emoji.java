package pl.sokolak.gites.emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sokolak.gites.category.Category;
import pl.sokolak.gites.tag.Tag;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @ManyToMany
    @JsonIgnoreProperties("emojis")
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("emojis")
    private List<Tag> tags = new ArrayList<>();

    public Emoji(String id, String hex, String dec) {
        this.id = id;
        this.hex = hex;
        this.dec = dec;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }
}
