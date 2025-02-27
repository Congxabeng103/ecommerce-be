package org.example.ecommerce_be.controller;


import org.example.ecommerce_be.entity.Color;
import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.service.ColorService;
import org.example.ecommerce_be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@CrossOrigin(origins = "http://localhost:5173")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    public List<Color> getAllColors() {
        return colorService.getAllColors();
    }

}
