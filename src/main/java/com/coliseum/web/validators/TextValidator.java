package com.coliseum.web.validators;

import com.coliseum.exceptions.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextValidator.class);

    public static boolean isValid(String login, String password) throws DataException {
        if (!isValidUsername(login)) {
            throw new DataException("Login is incorrect");
        } else if (!isValidPassword(password)) {
            throw new DataException("Password is incorrect");
        } else {
            return true;
        }
    }

    public static boolean isValidMail(String word) throws DataException {
        return validTextField(word, "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\" +\n" +
                "                    \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$", 20);
    }

    public static boolean isValidUsername(String word) throws DataException {
        return validTextField(word, "[A-Za-z]+[A-Za-z0-9]", 13);
    }

    public static boolean isValidPassword(String word) throws DataException {
        return validTextField(word, "", 16);
    }

    private static boolean validTextField(String word, String pattern, int length) throws DataException {
        try {
            if (!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
                Pattern pattern1 = Pattern.compile(pattern);
                Matcher matcher = pattern1.matcher(word);
                matcher.find();
                String found = matcher.group();
                if (!Objects.equals(found, "") && !Objects.isNull(found) && Objects.equals(found, word)) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Text is incorrect");
            throw new DataException("Text data is illegal");
        }
        return false;
    }
}
