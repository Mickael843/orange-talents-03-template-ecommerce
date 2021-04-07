package com.mikkaeru.ecommerce.validator;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private Class<?> clazz;
    private String domainAttribute;

    private final EntityManager entityManager;

    public UniqueValueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.clazz = constraintAnnotation.domainClass();
        this.domainAttribute = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        List<?> list = entityManager.createQuery(
                "SELECT 1 FROM "+clazz.getName()+ " WHERE UPPER("+domainAttribute+")=:value")
                .setParameter("value", value.toString().toUpperCase()).getResultList();

        return list.isEmpty();
    }
}
