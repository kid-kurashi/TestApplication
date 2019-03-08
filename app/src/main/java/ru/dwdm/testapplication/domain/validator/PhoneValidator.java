package ru.dwdm.testapplication.domain.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator extends Validator<String> {

    private static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");

    @Override
    public boolean validate(String phone) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        return matcher.find();
    }
}
