package org.vitalii.fedyk.realestateagency.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("variables")
@AllArgsConstructor
@Getter
public class AvailableLanguages {
    private String defaultLanguage;
    private String[] allLanguages;
}
