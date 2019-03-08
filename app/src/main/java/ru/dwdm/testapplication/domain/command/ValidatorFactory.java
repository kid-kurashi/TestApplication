package ru.dwdm.testapplication.domain.command;

import ru.dwdm.testapplication.domain.object.ValidationObject;

public class ValidatorFactory {

    public <T extends Validator<String>> T get(ValidationObject type) {
        switch (type) {
            case PASS:
                return (T) new PasswordValidator();
            case EMAIL:
                return (T) new EmailValidator();
            case PHONE:
                return (T) new PhoneValidator();
            case PHOTO:
                return (T) new PhotoValidator();
            default:
                return null;
        }
    }
}
