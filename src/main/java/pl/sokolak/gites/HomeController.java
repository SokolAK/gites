package pl.sokolak.gites;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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