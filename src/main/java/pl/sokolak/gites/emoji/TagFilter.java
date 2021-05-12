package pl.sokolak.gites.emoji;

import pl.sokolak.gites.tag.Tag;
import java.util.List;
import java.util.Set;

public interface TagFilter {
    List<Emoji> findAllContainingTagPhrases(Set<Tag> tags);
}
