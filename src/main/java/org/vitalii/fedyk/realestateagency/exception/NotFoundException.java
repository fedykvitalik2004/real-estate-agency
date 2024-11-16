package org.vitalii.fedyk.realestateagency.exception;

import java.util.Locale;

/**
 * Custom exception class that is thrown when a requested resource or entity is not found.
 * This class extends {@link RuntimeException}, making it an unchecked exception.
 * It can be used in scenarios where a specific resource, such as a database entry, file, or service,
 * cannot be located, allowing for more precise error handling.
 *
 * <p>Example usage:
 * <pre>
 * if (user == null) {
 *     throw new NotFoundException("User not found with the given ID");
 * }
 * </pre>
 * </p>
 *
 * @since 1.0
 */
public class NotFoundException extends LocalizedException{
    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final String messageKey, final Locale locale) {
        super(messageKey, locale);
    }
}
