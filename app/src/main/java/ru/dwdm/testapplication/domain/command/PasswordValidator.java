package ru.dwdm.testapplication.domain.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends Validator<String> {

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$",Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
