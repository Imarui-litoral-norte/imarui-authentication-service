package br.com.imarui.identity.identity.core.domain.exceptions.tenant;

public final class InvalidTenantCodeException extends RuntimeException {

    private InvalidTenantCodeException(String message) {
        super(message);
    }

    public static InvalidTenantCodeException blank() {
        return new InvalidTenantCodeException(
                "Tenant code cannot be blank."
        );
    }

    public static InvalidTenantCodeException tooShort(int minimumLength) {
        return new InvalidTenantCodeException(
                "Tenant code must contain at least "
                        + minimumLength
                        + " characters."
        );
    }

    public static InvalidTenantCodeException tooLong(int maximumLength) {
        return new InvalidTenantCodeException(
                "Tenant code cannot exceed "
                        + maximumLength
                        + " characters."
        );
    }

    public static InvalidTenantCodeException invalidFormat() {
        return new InvalidTenantCodeException(
                "Tenant code must start with a letter and contain "
                        + "only uppercase letters, numbers and underscores."
        );
    }
}