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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private EmojiService emojiService;

    @Value("${dataLoader.populate:false}")
    private boolean populate;
    @Value("${dataLoader.source:''}")
    private String source;
    @Value("${dataLoader.path.animals:''}")
    private String pathAnimals;
    @Value("${dataLoader.path.objects:''}")
    private String pathObjects;
    @Value("${dataLoader.path.symbols:''}")
    private String pathSymbols;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (populate) {
            emojiService.save(getEmojis(pathAnimals));
            emojiService.save(getEmojis(pathObjects));
            emojiService.save(getEmojis(pathSymbols));
        }
    }

    private List<Emoji> getEmojis(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Emoji> emojis = new ArrayList<>();
        if(source.equals("file")) {
            emojis = mapper.readValue(new File(path), new TypeReference<>() {});
        }
        if(source.equals("url")) {
            emojis = mapper.readValue(new URL(path), new TypeReference<>() {});
        }
        return emojis;
    }
}