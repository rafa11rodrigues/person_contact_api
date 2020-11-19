package com.rafatech.personcontactapi.infrastructure.exception;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageResolver {

    private static final String UNEXPECTED_ERROR_KEY = "unexpected_error";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/messages");


    public static String resolve(String messageKey, Object... args) {
        try {
            String messageTemplate = resourceBundle.getString(messageKey);
            return  MessageFormat.format(messageTemplate, args);
        } catch (MissingResourceException ex) {
            return messageKey.isBlank()
                    ? resolve(UNEXPECTED_ERROR_KEY)
                    : messageKey;
        } catch (NullPointerException ex) {
            return resolve(UNEXPECTED_ERROR_KEY);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
