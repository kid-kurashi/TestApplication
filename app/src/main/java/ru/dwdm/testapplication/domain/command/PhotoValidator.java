package ru.dwdm.testapplication.domain.command;

public class PhotoValidator extends Validator<String> {

    @Override
    public boolean validate(String path) {
        return (path != null && !path.isEmpty());
    }
}
