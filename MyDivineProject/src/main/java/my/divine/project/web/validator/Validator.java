package my.divine.project.web.validator;


import my.divine.project.exception.validate.ValidateException;

public interface Validator<T> {

        void  validate(T field) throws ValidateException;
    }

