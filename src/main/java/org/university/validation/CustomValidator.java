package org.university.validation;

import org.university.model.InputData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<LessThanM, InputData> {

    @Override
    public void initialize(LessThanM customAnnotation) {}

    @Override
    public boolean isValid(InputData inputData, ConstraintValidatorContext context) {
        if(inputData == null) {
            return true;
        }

        int m = inputData.getM();
        return inputData.getA() < m && inputData.getC() < m && inputData.getX0() < m;
    }
}
