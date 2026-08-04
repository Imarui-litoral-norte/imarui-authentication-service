package br.com.imarui.ima.identity.core.domain.exception.affiliation;

public class AffiliationAlreadyActiveException extends RuntimeException {

    public AffiliationAlreadyActiveException(String affiliationId) {
        super("Affiliation is already active. affiliationId=" + affiliationId);
    }
}