package pl.sokolak.gites.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import pl.sokolak.gites.emoji.Emoji;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("categories")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Emoji> emojis = new ArrayList<>();

    public Category(String id) {
        this.id = id;
    }

    public void addEmoji(Emoji emoji) {
        emojis.add(emoji);
    }

    public void addEmojis(List<Emoji> emojis) {
        emojis.addAll(emojis);
    }
}
