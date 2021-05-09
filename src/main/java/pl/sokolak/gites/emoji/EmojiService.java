package pl.sokolak.gites.emoji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import pl.sokolak.gites.tag.Tag;
import pl.sokolak.gites.tag.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmojiService {

    @Autowired
    private EmojiRepo emojiRepo;
//    @Autowired
//    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    public List<Emoji> getAll() {
        return emojiRepo.findAll();
    }

    public Optional<Emoji> findById(String id) {
        return emojiRepo.findById(id);
    }

    public List<Emoji> findByTagsStrings(List<String> tagsStrings) {
        Set<Tag> tags = tagsStrings.stream()
                .map(Tag::new)
                .collect(Collectors.toSet());
        return emojiRepo.findAllContainingTagPhrases(tags);
    }

    public void save(List<Emoji> emojis) {
        for (Emoji e : emojis)
            save(e);
    }

    @Transactional
    public void save(Emoji emoji) {
//        categoryService.save(emoji.getCategories());
        tagService.save(emoji.getTags());
        emojiRepo.save(emoji);
    }
}
