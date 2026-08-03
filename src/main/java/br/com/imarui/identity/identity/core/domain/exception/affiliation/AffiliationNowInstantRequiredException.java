package br.com.imarui.identity.identity.core.domain.exception.affiliation;

public class AffiliationNowInstantRequiredException extends RuntimeException {

    public AffiliationNowInstantRequiredException() {
        super("Affiliation operation instant is required.");
    }
}
