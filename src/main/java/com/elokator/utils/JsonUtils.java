package com.elokator.utils;

import com.elokator.exceptions.AppCoreException;

public interface JsonUtils {
    <T> String toJson(T value) throws AppCoreException;
    <T> T fromJson(String json, Class<T> clazz) throws AppCoreException;
}
