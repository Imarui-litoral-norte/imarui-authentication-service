package br.com.imarui.identity.platform.web.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@RequiredArgsConstructor
public class GlobalWebExceptionHandler extends ResponseEntityExceptionHandler {

    private final Clock clock;

    @Override
    protected ResponseEntity<Object> createResponseEntity(
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        if (body instanceof ProblemDetail problem) {
            if (
                    problem.getProperties() == null
                            || !problem.getProperties().containsKey("code")
            ) {
                problem.setProperty(
                        "code",
                        "HTTP_" + statusCode.value()
                );
            }

            problem.setProperty(
                    "timestamp",
                    clock.instant()
            );
        }

        return super.createResponseEntity(
                body,
                headers,
                statusCode,
                request
        );
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedException(
            Exception exception
    ) {
        ProblemDetail problem =
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An unexpected error occurred."
                );

        problem.setProperty(
                "code",
                "INTERNAL_SERVER_ERROR"
        );

        problem.setProperty(
                "timestamp",
                clock.instant()
        );

        return problem;
    }
}