package br.com.imarui.ima.identity.core.domain.exception.tenant;

import lombok.Getter;

@Getter
public final class InvalidTenantNameException extends RuntimeException {

    private final Violation violation;
    private final Integer maximumLength;

    private InvalidTenantNameException(
            String message,
            Violation violation,
            Integer maximumLength
    ) {
        super(message);
        this.violation = violation;
        this.maximumLength = maximumLength;
    }

    public static InvalidTenantNameException blank() {
        return new InvalidTenantNameException(
                "TenantName value must not be blank.",
                Violation.BLANK,
                null
        );
    }

    public static InvalidTenantNameException tooLong(
            int maximumLength
    ) {
        return new InvalidTenantNameException(
                "TenantName value must not exceed "
                        + maximumLength
                        + " characters.",
                Violation.TOO_LONG,
                maximumLength
        );
    }

    public enum Violation {
        BLANK,
        TOO_LONG
    }
}
