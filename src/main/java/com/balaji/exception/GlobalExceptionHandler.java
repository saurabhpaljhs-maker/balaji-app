package com.balaji.exception;

import com.balaji.exception.BalajiExceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // ── 1. Validation errors ──────────────────────────────────────────────────
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(MethodArgumentNotValidException ex,
                                     HttpServletRequest req) {
        BindingResult br = ex.getBindingResult();
        List<ApiError.FieldError> fieldErrors = br.getFieldErrors().stream()
                .map(fe -> new ApiError.FieldError(
                        fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue()))
                .collect(Collectors.toList());
        log.warn("Validation failed [{}]", req.getRequestURI());
        return ApiError.builder()
                .status(400).error("Validation Failed")
                .message("Please check the highlighted fields")
                .path(req.getRequestURI())
                .fieldErrors(fieldErrors)
                .build();
    }

    // ── 2. Resource not found (404) ───────────────────────────────────────────
    @ExceptionHandler(ResourceNotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(ResourceNotFoundEx ex, HttpServletRequest req) {
        log.warn("Not found: {}", ex.getMessage());
        return ApiError.of(404, "Not Found", ex.getMessage(), req.getRequestURI());
    }

    // ── 3. Business errors (400) ──────────────────────────────────────────────
    @ExceptionHandler(BusinessEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBusiness(BusinessEx ex, HttpServletRequest req) {
        log.warn("Business error: {}", ex.getMessage());
        return ApiError.of(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    }

    // ── 4. Payment errors (402) ───────────────────────────────────────────────
    @ExceptionHandler(PaymentEx.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public ApiError handlePayment(PaymentEx ex, HttpServletRequest req) {
        log.error("Payment error: {}", ex.getMessage());
        return ApiError.of(402, "Payment Error", ex.getMessage(), req.getRequestURI());
    }

    // ── 5. File too large (413) ───────────────────────────────────────────────
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ApiError handleFileTooLarge(HttpServletRequest req) {
        return ApiError.of(413, "File Too Large",
                "Maximum file size is 10MB.", req.getRequestURI());
    }

    // ── 6. Missing param (400) ────────────────────────────────────────────────
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMissingParam(MissingServletRequestParameterException ex,
                                        HttpServletRequest req) {
        return ApiError.of(400, "Missing Parameter",
                "Required parameter '" + ex.getParameterName() + "' is missing",
                req.getRequestURI());
    }

    // ── 7. Wrong type param (400) ─────────────────────────────────────────────
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                        HttpServletRequest req) {
        return ApiError.of(400, "Invalid Parameter",
                "'" + ex.getName() + "' has invalid value: " + ex.getValue(),
                req.getRequestURI());
    }

    // ── 8. Access denied (403) ────────────────────────────────────────────────
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleAccessDenied(HttpServletRequest req) {
        log.warn("Access denied: {}", req.getRequestURI());
        return ApiError.of(403, "Access Denied",
                "You don't have permission", req.getRequestURI());
    }

    // ── 9. Auth required (401) ────────────────────────────────────────────────
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleAuth(HttpServletRequest req) {
        return ApiError.of(401, "Unauthorized",
                "Please login to continue", req.getRequestURI());
    }

    // ── 10. Static resource not found — IGNORE karo, Spring handle karega ─────
    @ExceptionHandler({NoResourceFoundException.class, NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNoResource(Exception ex, HttpServletRequest req) {
        // CSS/JS/Images ke liye log mat karo — sirf API errors log karo
        String uri = req.getRequestURI();
        if (!uri.startsWith("/api/")) {
            log.debug("Resource not found: {}", uri);
        }
        return ApiError.of(404, "Not Found",
                "Resource not found: " + uri, uri);
    }

    // ── 11. Catch-all (500) ───────────────────────────────────────────────────
    // IMPORTANT: Static resources (CSS/JS) ke liye ye NAHI chalega
    // kyunki wo Spring ke ResourceHttpRequestHandler se serve hote hain
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleGeneric(Exception ex, HttpServletRequest req) {
        String uri = req.getRequestURI();
        // Static resources ki galti ignore karo
        if (uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/images/")) {
            log.error("Static resource error [{}]: {}", uri, ex.getMessage());
            return ApiError.of(404, "Not Found", "Static resource not found", uri);
        }
        log.error("UNEXPECTED ERROR [{}]: {}", uri, ex.getMessage(), ex);
        return ApiError.of(500, "Server Error",
                "Something went wrong. Contact: 8299576949", uri);
    }
}
