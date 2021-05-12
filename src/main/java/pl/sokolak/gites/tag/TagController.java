package pl.sokolak.gites.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    private List<Tag> getTags() {
        return tagService.list();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Tag> getTag(@PathVariable String id) {
        Optional<Tag> tag = tagService.findById(id);
        return tag.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
