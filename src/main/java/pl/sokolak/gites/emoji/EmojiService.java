package pl.sokolak.gites.emoji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sokolak.gites.category.CategoryService;
import pl.sokolak.gites.tag.TagService;

import java.util.List;
import java.util.Optional;

@Service
public class EmojiService {

    @Autowired
    private pl.sokolak.gites.emoji.EmojiRepo emojiRepo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    public List<pl.sokolak.gites.emoji.Emoji> list() {
        return emojiRepo.findAll();
    }

    public Optional<pl.sokolak.gites.emoji.Emoji> findById(String id) {
        return emojiRepo.findById(id);
    }

    public void save(List<pl.sokolak.gites.emoji.Emoji> emojis) {
        for (pl.sokolak.gites.emoji.Emoji e : emojis)
            save(e);
    }

    public void save(pl.sokolak.gites.emoji.Emoji emoji) {
        categoryService.save(emoji.getCategories());
        tagService.save(emoji.getTags());
        emojiRepo.save(emoji);
    }
}
