package pl.sokolak.gites.emoji;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emojis")
public class EmojiController {

    @Autowired
    private EmojiService emojiService;

    @GetMapping
    private List<Emoji> getEmojis() {
        return emojiService.list();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Emoji> getEmoji(@PathVariable String id) {
        Optional<Emoji> emoji = emojiService.findById(id);
        return emoji.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/populate")
    private void populate(HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Emoji> emojis = mapper.readValue(new File("src/main/resources/data.json"), new TypeReference<>() {});
        emojiService.save(emojis);
        response.sendRedirect("/api/emojis");
    }
}
