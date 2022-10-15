
package com.example.demo.Servicio;

import com.example.demo.Repositorio.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Modelo.Category;
import java.util.Optional;


@Service

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAll(){
        return categoryRepository.getAll();
    }
    
    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }
    
    public Category save (Category category){
        if (category.getId() == null){
            return categoryRepository.save(category);
        } else {
            Optional<Category> category1 = categoryRepository.getCategory(category.getId());
            if(category1.isEmpty()){
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    public Category update (Category category) {
        if(category.getId() != null) {
            Optional<Category> categoryFound = categoryRepository.getCategory(category.getId());
            if(!categoryFound.isEmpty()) {
                if(category.getDescription() != null){
                    categoryFound.get().setDescription(category.getDescription());
                }
                if(category.getName() != null){
                    categoryFound.get().setName(category.getName());
                }
                return categoryRepository.save(categoryFound.get());
            }
        }
        return category;
    }

    public boolean delete(int id) {
        Boolean resultado = getCategory(id).map(element -> {
            categoryRepository.delete(element);
            return true;
        }).orElse(false);
        return resultado;
    }


}
