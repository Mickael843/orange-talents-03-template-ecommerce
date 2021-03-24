package com.mikkaeru.ecommerce.repository.product;

import com.mikkaeru.ecommerce.model.product.ProductImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
}
