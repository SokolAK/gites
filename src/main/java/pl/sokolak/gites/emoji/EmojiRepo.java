package pl.sokolak.gites.emoji;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sokolak.gites.tag.Tag;

import java.util.List;
import java.util.Set;

public interface EmojiRepo extends JpaRepository<Emoji, String>, TagFilter {
    long count();
}
