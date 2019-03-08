package ru.dwdm.testapplication.domain.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends Validator<String> {

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$");

    @Override
    public boolean validate(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
