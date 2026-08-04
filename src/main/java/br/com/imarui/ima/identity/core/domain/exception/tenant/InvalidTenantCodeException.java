package br.com.imarui.ima.identity.core.domain.exception.tenant;

import lombok.Getter;

@Getter
public final class InvalidTenantCodeException extends RuntimeException {

    private final Violation violation;
    private final Integer minimumLength;
    private final Integer maximumLength;

    private InvalidTenantCodeException(
            String message,
            Violation violation,
            Integer minimumLength,
            Integer maximumLength
    ) {
        super(message);
        this.violation = violation;
        this.minimumLength = minimumLength;
        this.maximumLength = maximumLength;
    }

    public static InvalidTenantCodeException blank() {
        return new InvalidTenantCodeException(
                "Tenant code cannot be blank.",
                Violation.BLANK,
                null,
                null
        );
    }

    public static InvalidTenantCodeException tooShort(int minimumLength) {
        return new InvalidTenantCodeException(
                "Tenant code must contain at least "
                        + minimumLength
                        + " characters.",
                Violation.TOO_SHORT,
                minimumLength,
                null
        );
    }

    public static InvalidTenantCodeException tooLong(int maximumLength) {
        return new InvalidTenantCodeException(
                "Tenant code cannot exceed "
                        + maximumLength
                        + " characters.",
                Violation.TOO_LONG,
                null,
                maximumLength
        );
    }

    public static InvalidTenantCodeException invalidFormat() {
        return new InvalidTenantCodeException(
                "Tenant code must start with a letter and contain "
                        + "only uppercase letters, numbers and underscores.",
                Violation.INVALID_FORMAT,
                null,
                null
        );
    }

    public enum Violation {
        BLANK,
        TOO_SHORT,
        TOO_LONG,
        INVALID_FORMAT
    }
}
