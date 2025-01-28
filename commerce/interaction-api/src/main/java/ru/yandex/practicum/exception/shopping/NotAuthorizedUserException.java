package ru.yandex.practicum.exception.shopping;

import lombok.Getter;

@Getter
public class NotAuthorizedUserException extends RuntimeException {
    public NotAuthorizedUserException(String message) {
        super(message);
    }
}
