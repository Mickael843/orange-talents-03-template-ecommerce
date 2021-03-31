package com.mikkaeru.ecommerce.validator;

import com.mikkaeru.ecommerce.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@DataJpaTest
class UniqueValueValidatorTest {

    @PersistenceContext
    private EntityManager entityManager;
    private ConstraintValidatorContext constraintValidatorContext;

    private UniqueValueValidator validator;

    private static final String FIELD_NAME = "login";
    private static final Class<?> DOMAIN_CLASS = User.class;

    @BeforeEach
    public void setUp() {
        this.constraintValidatorContext = mock(ConstraintValidatorContext.class);

        validator = new UniqueValueValidator(entityManager);

        UniqueValueValidatorTestCase testCase = new UniqueValueValidatorTestCase();

        validator.initialize(testCase);
    }

    @Test
    @DisplayName("Ao fornecer um valor valido deve retornar true")
    void GIVEN_ValidValue_MUST_ReturnTrue() {
        boolean isValid = validator.isValid("teste001@gmail.com", constraintValidatorContext);
        assertTrue(isValid);
    }

    @Test
    @DisplayName("Ao fornecer um valor inválido deve retornar false")
    void GIVEN_InvalidValue_MUST_ReturnFalse() {
        boolean isValid = validator.isValid("teste002@gmail.com", constraintValidatorContext);
        assertFalse(isValid);
    }

    private static class UniqueValueValidatorTestCase implements UniqueValue {

        @Override
        public String message() {
            return "Test Message";
        }

        @Override
        public Class<?>[] groups() {
            return new Class[]{};
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return new Class[]{};
        }

        @Override
        public String fieldName() {
            return FIELD_NAME;
        }

        @Override
        public Class<?> domainClass() {
            return DOMAIN_CLASS;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return UniqueValue.class;
        }
    }
}