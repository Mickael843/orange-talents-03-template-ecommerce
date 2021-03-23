package com.mikkaeru.ecommerce.repository.product;

import com.mikkaeru.ecommerce.model.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
