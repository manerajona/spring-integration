package com.manerajona.common.domain;

public record CardValidation(boolean isValid) {
    public boolean isNotValid() {
        return !isValid;
    }
}