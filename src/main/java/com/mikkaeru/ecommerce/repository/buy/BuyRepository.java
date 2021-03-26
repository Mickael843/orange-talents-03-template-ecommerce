package com.mikkaeru.ecommerce.repository.buy;

import com.mikkaeru.ecommerce.model.buy.Buy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends CrudRepository<Buy, Long> {
}
