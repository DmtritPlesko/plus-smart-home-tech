package ru.yandex.practicum.exception;

import lombok.Getter;

@Getter
public class NotAuthorizedUserException extends RuntimeException {
    public NotAuthorizedUserException(String message) {
        super(message);
    }
}
