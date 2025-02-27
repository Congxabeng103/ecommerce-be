package org.example.ecommerce_be.controller;


import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.entity.Size;
import org.example.ecommerce_be.service.ProductService;
import org.example.ecommerce_be.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
@CrossOrigin(origins = "http://localhost:5173")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping
    public List<Size> getAllSizes() {
        return sizeService.getAllSizes();
    }

}