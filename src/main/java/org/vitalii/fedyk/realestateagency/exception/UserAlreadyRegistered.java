package org.vitalii.fedyk.realestateagency.exception;

import java.util.List;
import java.util.Locale;

public class UserAlreadyRegistered extends LocalizedException {
    public UserAlreadyRegistered(final String messageKey, final List<String> elements) {
        super(messageKey, elements);
    }
}
