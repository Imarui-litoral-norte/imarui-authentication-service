package br.com.imarui.identity.identity.api.exception;

import br.com.imarui.identity.identity.api.controller.tenant.AdminTenantController;
import br.com.imarui.identity.identity.core.application.exception.tenant.TenantCodeAlreadyExistsException;
import br.com.imarui.identity.identity.core.application.exception.tenant.TenantNotFoundException;
import br.com.imarui.identity.identity.core.domain.exception.tenant.TenantActivationNotAllowedException;
import br.com.imarui.identity.identity.core.domain.exception.tenant.TenantDisableNotAllowedException;
import br.com.imarui.identity.identity.core.domain.exception.tenant.TenantReactivationNotAllowedException;
import br.com.imarui.identity.identity.core.domain.exception.tenant.TenantRenameNotAllowedException;
import br.com.imarui.identity.platform.web.exception.ApiProblemFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice(
        basePackageClasses = AdminTenantController.class
)
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class TenantExceptionHandler {

    private final ApiProblemFactory problemFactory;

    @ExceptionHandler(TenantNotFoundException.class)
    public ProblemDetail handleTenantNotFound(
            TenantNotFoundException exception,
            Locale locale
    ) {
        return problemFactory.create(
                HttpStatus.NOT_FOUND,
                "TENANT_NOT_FOUND",
                "tenant.not-found",
                locale,
                exception.getTenantId().value()
        );
    }

    @ExceptionHandler(TenantCodeAlreadyExistsException.class)
    public ProblemDetail handleCodeAlreadyExists(
            TenantCodeAlreadyExistsException exception,
            Locale locale
    ) {
        return problemFactory.create(
                HttpStatus.CONFLICT,
                "TENANT_CODE_ALREADY_EXISTS",
                "tenant.code-already-exists",
                locale,
                exception.getTenantCode().value()
        );
    }

    @ExceptionHandler(TenantActivationNotAllowedException.class)
    public ProblemDetail handleActivationNotAllowed(
            TenantActivationNotAllowedException exception,
            Locale locale
    ) {
        return problemFactory.create(
                HttpStatus.CONFLICT,
                "TENANT_ACTIVATION_NOT_ALLOWED",
                "tenant.activation-not-allowed",
                locale,
                exception.getCurrentStatus()
        );
    }

    @ExceptionHandler(TenantDisableNotAllowedException.class)
    public ProblemDetail handleDisableNotAllowed(
            TenantDisableNotAllowedException exception,
            Locale locale
    ) {
        return problemFactory.create(
                HttpStatus.CONFLICT,
                "TENANT_DISABLE_NOT_ALLOWED",
                "tenant.disable-not-allowed",
                locale,
                exception.getCurrentStatus()
        );
    }

    @ExceptionHandler(TenantReactivationNotAllowedException.class)
    public ProblemDetail handleReactivationNotAllowed(
            TenantReactivationNotAllowedException exception,
            Locale locale
    ) {
        return problemFactory.create(
                HttpStatus.CONFLICT,
                "TENANT_REACTIVATION_NOT_ALLOWED",
                "tenant.reactivation-not-allowed",
                locale,
                exception.getCurrentStatus()
        );
    }

    @ExceptionHandler(TenantRenameNotAllowedException.class)
    public ProblemDetail handleRenameNotAllowed(
            TenantRenameNotAllowedException exception,
            Locale locale
    ) {
        return problemFactory.create(
                HttpStatus.CONFLICT,
                "TENANT_RENAME_NOT_ALLOWED",
                "tenant.rename-not-allowed",
                locale,
                exception.getCurrentStatus()
        );
    }
}
