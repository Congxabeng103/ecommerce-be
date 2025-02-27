package org.example.ecommerce_be.service;

import org.example.ecommerce_be.entity.Color;
import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.repository.ColorRepository;
import org.example.ecommerce_be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }
}
