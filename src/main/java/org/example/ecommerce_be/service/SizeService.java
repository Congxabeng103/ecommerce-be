package org.example.ecommerce_be.service;

import org.example.ecommerce_be.entity.Product;
import org.example.ecommerce_be.entity.Size;
import org.example.ecommerce_be.repository.ProductRepository;
import org.example.ecommerce_be.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }
}
