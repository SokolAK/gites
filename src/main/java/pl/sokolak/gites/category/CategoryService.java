package pl.sokolak.gites.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> list() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findById(String id) {
        return categoryRepo.findById(id);
    }

    public void save(List<Category> categories) {
        for (Category category : categories) {
            save(category);
        }
    }

    public void save(Category category) {
        Optional<Category> savedCategory = findById(category.getId());
        savedCategory.ifPresentOrElse(
                cat -> {
                    cat.addEmojis(category.getEmojis());
                    categoryRepo.save(cat);
                },
                () -> categoryRepo.save(category)
        );
    }
}
