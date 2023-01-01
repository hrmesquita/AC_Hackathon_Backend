package org.greytcoders.rent_a_bs.repositories;

import org.greytcoders.rent_a_bs.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends CrudRepository<Product, Long> {
}
