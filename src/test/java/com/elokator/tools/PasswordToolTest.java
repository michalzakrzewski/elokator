package com.elokator.tools;

import com.elokator.exceptions.AppCoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PasswordToolTest {

    @Test
    @DisplayName("Should encrypt password correctly")
    void correctPassword_shouldEncryptPasswordCorrectly() throws AppCoreException {
        final String password = "Aa123456789!";
        final String encryptPassword = PasswordTool.encryptPassword(password);
        System.out.println(encryptPassword);
        assertNotNull(encryptPassword);
    }

    @Test
    @DisplayName("Should decrypt password correctly")
    void correctPassword_shouldDecryptPasswordCorrectly() throws AppCoreException {
        final String expectedPassword = "Aa123456789!";
        final String password = "xC3IyH1dkEzmUlx9kqEwXA==";
        final String decryptPassword = PasswordTool.decryptPassword(password);
        assertNotNull(decryptPassword);
        assertEquals(expectedPassword, decryptPassword);
    }
}