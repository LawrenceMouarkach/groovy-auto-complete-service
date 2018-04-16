package com.lawrencemouarkach.service.autocomplete.api.common

import static javax.ws.rs.core.Response.Status.*

enum ErrorDetail {
    COMMON_INVALID_URL_PARAMETER(BAD_REQUEST.getStatusCode(), "The requested URL contains an invalid parameter."),
    COMMON_URL_NOT_FOUND(NOT_FOUND.getStatusCode(), "The requested URL was not found."),
    COMMON_METHOD_NOT_ALLOWED(
            METHOD_NOT_ALLOWED.getStatusCode(), "This resource does not support the requested method."),
    COMMON_MISSING_HEADER(BAD_REQUEST.getStatusCode(), "The header has missing mandatory fields."),
    COMMON_INVALID_HEADER(BAD_REQUEST.getStatusCode(), "One or more headers have invalid values."),
    COMMON_JSON_SYNTAX_ERROR(BAD_REQUEST.getStatusCode(), "The request body does not contain a valid JSON."),
    COMMON_MISSING_QUERY_PARAMETER(BAD_REQUEST.getStatusCode(), "One or more required query parameter(s) missing."),
    COMMON_INVALID_VALUES(BAD_REQUEST.getStatusCode(), "There are validation errors."),
    COMMON_DATA_JSON_ERROR(BAD_REQUEST.getStatusCode(), "The body has missing mandatory fields."),
    COMMON_UNAUTHORISED_ERROR(UNAUTHORIZED.getStatusCode(), "You must be logged in to make this request."),
    COMMON_SERVICE_ERROR(INTERNAL_SERVER_ERROR.getStatusCode(), "Internal Service Exception."),
    COMMON_UNSUPPORTED_MEDIA_TYPE(UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "The media type selected in the request's 'Content-Type' header is not supported."),
    REMOTE_SERVICE_ERROR(INTERNAL_SERVER_ERROR.getStatusCode(), "Remote Service Exception.");

    private final int status
    private final String message

    ErrorDetail(int status, String message) {
        this.status = status
        this.message = message
    }

    int getStatus() {
        return status
    }

    String getMessage() {
        return message
    }
}
