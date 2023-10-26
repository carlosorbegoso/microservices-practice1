package com.skyblue.userservice.dto.response;

import org.springframework.http.HttpStatus;

public record ApiResponse(String message, boolean success, HttpStatus status) {
}
