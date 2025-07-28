package com.elokator.utils;

@FunctionalInterface
public interface ThrowingSupplier<T> {
    T get() throws Exception;
}
