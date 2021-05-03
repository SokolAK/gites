package pl.sokolak.gites.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    private List<Category> getCategories() {
        return categoryService.list();
    }

    @GetMapping("/{id}")
    private Optional<Category> getCategory(@PathVariable String id) {
        return categoryService.findById(id);
    }

}
