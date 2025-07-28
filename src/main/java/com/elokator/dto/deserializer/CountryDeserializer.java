package com.elokator.dto.deserializer;

import com.elokator.enums.CountryEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CountryDeserializer extends JsonDeserializer<CountryEnum> {
    private static final Logger LOG = LoggerFactory.getLogger(CountryDeserializer.class);

    @Override
    public CountryEnum deserialize(JsonParser p, DeserializationContext ctxt) {
        String code;
        try {
            code = p.getText();
            if (StringUtils.isNotEmpty(code) && code.length() == 2) {
                return CountryEnum.getCountryByAlpha2Code(code);
            }
            if (StringUtils.isNotEmpty(code) && code.length() == 3) {
                return CountryEnum.getCountryByAlpha3Code(code);
            }
        } catch (IOException e) {
            LOG.error("Caught IOException during deserialize country enum", e);
            return null;
        }
        LOG.error("Invalid country code {}", code);
        return null;
    }
}
