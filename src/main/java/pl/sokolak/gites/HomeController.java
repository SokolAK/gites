package pl.sokolak.gites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sokolak.gites.emoji.EmojiService;

import java.util.concurrent.TimeUnit;

@Controller("/")
public class HomeController {

    @Autowired
    private EmojiService emojiService;
    @Value("${database.delay:1}")
    private int databaseDelay;


    @RequestMapping
    public String home() {
        return "index.html";
    }

    @CrossOrigin
    @GetMapping("api/status")
    //@ResponseStatus(code = HttpStatus.OK)
    private ResponseEntity<Void> getStatus() {
        long itemsNo1 = emojiService.count();
        try {
            TimeUnit.SECONDS.sleep(databaseDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long itemsNo2 = emojiService.count();

        if(itemsNo1 == itemsNo2) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}