package com.mikkaeru.ecommerce.repository.product.question;

import com.mikkaeru.ecommerce.model.product.question.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
