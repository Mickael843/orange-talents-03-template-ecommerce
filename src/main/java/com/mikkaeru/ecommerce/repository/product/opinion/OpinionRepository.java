package com.mikkaeru.ecommerce.repository.product.opinion;

import com.mikkaeru.ecommerce.model.product.opinion.Opinion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Long> {

    @Query(value = "SELECT COUNT(rating) FROM opinion WHERE product_id = ?1", nativeQuery = true)
    Integer getTotalRating(Long id);

    @Query(value = "SELECT FORMAT(AVG(rating), 2) FROM opinion WHERE product_id = ?1", nativeQuery = true)
    Double getAverageRating(Long id);
}
