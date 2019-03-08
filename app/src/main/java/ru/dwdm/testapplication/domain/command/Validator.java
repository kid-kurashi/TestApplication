package ru.dwdm.testapplication.domain.command;

public abstract class Validator<O> {
    public abstract boolean validate(O object);
}
