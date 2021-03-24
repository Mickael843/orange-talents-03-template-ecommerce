package com.mikkaeru.ecommerce.repository.product.opinion;

import com.mikkaeru.ecommerce.model.product.opinion.Opinion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Long> {
}
