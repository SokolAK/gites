package pl.sokolak.gites.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    private List<Tag> getTags() {
        return tagService.list();
    }

    @GetMapping("/{id}")
    private Optional<Tag> getTag(@PathVariable String id) {
        return tagService.findById(id);
    }
}
