package pl.sokolak.gites.emoji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emojis")
public class EmojiController {

    @Autowired
    private EmojiService emojiService;

    @GetMapping()
    private List<Emoji> getEmoji(@RequestParam(name = "tag", required = false) List<String> tagStrings) {
        if(tagStrings == null) {
            return emojiService.getAll();
        }
        return emojiService.findByTagsStrings(tagStrings);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Emoji> getEmoji(@PathVariable String id) {
        Optional<Emoji> emoji = emojiService.findById(id);
        return emoji.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
