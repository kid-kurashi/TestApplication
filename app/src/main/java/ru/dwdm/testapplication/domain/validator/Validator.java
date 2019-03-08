package ru.dwdm.testapplication.domain.validator;

public abstract class Validator<T> {
    abstract boolean validate(T object);
}
