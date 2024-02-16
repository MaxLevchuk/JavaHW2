package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dto.category.CategoryCreateDTO;
import org.example.entities.CategoryEntity;
import org.example.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> index() {
        List<CategoryEntity> list = categoryRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryEntity> create(@ModelAttribute CategoryCreateDTO dto) {
        CategoryEntity category = new CategoryEntity();
        category.setName(dto.getName());
        category.setImage(dto.getImage());
        category.setDescription(dto.getDescription());
        category.setCreationTime(LocalDateTime.now());
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping(value = "/{categoryId}/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryEntity> edit(@PathVariable Long categoryId, @ModelAttribute CategoryCreateDTO dto) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(categoryId.intValue());
        if (optionalCategory.isPresent()) {
            CategoryEntity category = optionalCategory.get();
            category.setName(dto.getName());
            category.setImage(dto.getImage());
            category.setDescription(dto.getDescription());
            category.setLastUpdateTime(LocalDateTime.now());
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categoryId}/delete")
    public ResponseEntity<?> delete(@PathVariable Long categoryId) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(categoryId.intValue());
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(categoryId.intValue());
            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }
}
