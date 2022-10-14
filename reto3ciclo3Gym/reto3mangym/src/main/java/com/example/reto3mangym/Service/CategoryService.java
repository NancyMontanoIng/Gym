package com.example.reto3mangym.Service;

import com.example.reto3mangym.Model.Category;
import Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return (List<Category>)categoryRepository.getAll();
    }
    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category){
        if(category.getId() == null){
            return categoryRepository.save(category);
        } else  {
            Optional <Category> categoryEncontrado = categoryRepository.getCategory(category.getId());
            if(categoryEncontrado.isPresent()){
                return categoryRepository.save(category);
            } else{
                return category;
            }
        }
    }

    public Category update(Category category) {
        if (category.getId() != null) {
            Optional<Category> categoryEncontrado = categoryRepository.getCategory(category.getId());
            if(!categoryEncontrado.isPresent()){
                if(category.getDescription() != null){
                    categoryEncontrado.get().setDescription(category.getDescription());
                }
                if(category.getName() != null){
                    categoryEncontrado.get().setName((category.getName()));
                }
                return categoryRepository.save(categoryEncontrado.get());
            }

        }
        return category;
    }

    public boolean delete(int Id){
        Boolean resultado = getCategory(Id).map(PorEliminar -> {
            categoryRepository.delete(PorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}
