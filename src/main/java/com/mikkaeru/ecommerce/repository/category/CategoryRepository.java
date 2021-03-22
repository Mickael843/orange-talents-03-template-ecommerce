package com.mikkaeru.ecommerce.repository.category;

import com.mikkaeru.ecommerce.domain.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
