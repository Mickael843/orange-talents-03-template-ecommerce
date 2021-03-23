package com.mikkaeru.ecommerce.repository.characteristic;

import com.mikkaeru.ecommerce.model.characteristic.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
