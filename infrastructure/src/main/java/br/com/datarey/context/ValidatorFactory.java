package br.com.datarey.context;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorFactory {

    private Validator validator;
    
    @Produces
    public synchronized Validator getValidator(){
        if(validator == null){
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        
        return validator;
    }
}
