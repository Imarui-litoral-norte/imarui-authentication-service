package br.com.imarui.ima.platform.web.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ApiProblemFactory {

    private final MessageSource messageSource;
    private final Clock clock;

    public ProblemDetail create(
            HttpStatus status,
            String code,
            String messageKey,
            Locale locale,
            Object... arguments
    ) {
        String detail = messageSource.getMessage(
                messageKey,
                arguments,
                messageKey,
                locale
        );

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                detail
        );

        problem.setProperty("code", code);
        problem.setProperty(
                "timestamp",
                clock.instant()
        );

        return problem;
    }
}