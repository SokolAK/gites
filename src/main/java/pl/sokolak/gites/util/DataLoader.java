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
import java.net.URL;
import java.util.Comparator;
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
            //URL url = new URL("https://7521680b-4b85-40f2-bf3f-dd2ac91f400f.usrfiles.com/ugd/752168_405bc89b17994a54a75bd46bb746e8fc.txt");
            //List<Emoji> emojis = mapper.readValue(url, new TypeReference<>() {});
            List<Emoji> emojis = mapper.readValue(new File("src/main/resources/data.json"), new TypeReference<>() {});
            //emojis.sort(Comparator.comparing(Emoji::getName));
            emojiService.save(emojis);
        }
    }
}