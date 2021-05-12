package pl.sokolak.gites.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepo tagRepo;

    public List<Tag> list() {
        return tagRepo.findAll();
    }

    public Optional<Tag> findById(String id) {
        return tagRepo.findById(id);
    }

    public void save(List<Tag> tags) {
        for (Tag tag : tags) {
            save(tag);
        }
    }

    @Transactional
    public void save(Tag tag) {
        Optional<Tag> savedTag = findById(tag.getName());
        savedTag.ifPresentOrElse(
                t -> {
                    t.addEmojis(tag.getEmojis());
                    tagRepo.save(t);
                },
                () -> tagRepo.save(tag)
        );
    }
}
