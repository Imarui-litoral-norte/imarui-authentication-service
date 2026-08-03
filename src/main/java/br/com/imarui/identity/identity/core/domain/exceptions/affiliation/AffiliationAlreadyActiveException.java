package br.com.imarui.identity.identity.core.domain.exceptions.affiliation;

public class AffiliationAlreadyActiveException extends RuntimeException {

    public AffiliationAlreadyActiveException(String affiliationId) {
        super("Affiliation is already active. affiliationId=" + affiliationId);
    }
}