package com.mikkaeru.ecommerce.repository.user;

import com.mikkaeru.ecommerce.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
