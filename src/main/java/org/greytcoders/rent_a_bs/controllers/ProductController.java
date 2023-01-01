package org.greytcoders.rent_a_bs.controllers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.greytcoders.rent_a_bs.models.Product;
import org.greytcoders.rent_a_bs.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    private final ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        LOGGER.info("operation='getAllProducts'");

        Optional<Iterable<Product>> allProducts = productsService.getAll();
        if (allProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allProducts.get(), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        LOGGER.info("operation='getProductById', id='{}'", id);

        Optional<Product> opProduct = productsService.getProductById(id);
        if (opProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(opProduct.get(), HttpStatus.OK);
        }
    }



}
