package org.greytcoders.rent_a_bs.service;

import org.greytcoders.rent_a_bs.models.Product;
import org.greytcoders.rent_a_bs.repositories.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepo productsRepo;

    public Product insert(Product product) {
        Product newProduct = productsRepo.save(product);
        return newProduct;
    }

    public Optional<Iterable<Product>> getAll() {
        return Optional.of(productsRepo.findAll());
    }

    public Optional<Product> getProductById(Long id) {
        return productsRepo.findById(id);
    }

}
