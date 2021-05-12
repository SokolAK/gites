package pl.sokolak.gites;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sokolak.gites.emoji.Emoji;

import java.util.Optional;

@Controller("/")
public class HomeController {

    @RequestMapping
    public String home() {
        return "index.html";
    }

    @CrossOrigin
    @GetMapping("api/status")
    @ResponseStatus(code = HttpStatus.OK, reason = "Some parameters are invalid")
    private void getStatus() {
    }
}