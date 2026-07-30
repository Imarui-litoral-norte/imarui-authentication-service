package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class AffiliationAlreadyActiveException extends RuntimeException {

    public AffiliationAlreadyActiveException(String affiliationId) {
        super("Affiliation is already active. affiliationId=" + affiliationId);
    }
}