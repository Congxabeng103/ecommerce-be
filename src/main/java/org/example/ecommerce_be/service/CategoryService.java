package org.example.ecommerce_be.service;

import org.example.ecommerce_be.entity.Category;
import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.repository.CategoryRepository;
import org.example.ecommerce_be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService{

@Autowired
private CategoryRepository categoryRepository;

public List<Category> getAllCategories() {
    return categoryRepository.findAll();
}
}
