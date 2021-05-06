package pl.sokolak.gites.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.sokolak.gites.emoji.Emoji;
import pl.sokolak.gites.emoji.EmojiService;

import java.io.File;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private EmojiService emojiService;

    @Value("${dataLoader.populate:false}")
    private boolean populate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (populate) {
            ObjectMapper mapper = new ObjectMapper();
            List<Emoji> emojis = mapper.readValue(new File("src/main/resources/data.json"), new TypeReference<>() {
            });
            emojiService.save(emojis);
        }
    }
}