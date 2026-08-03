package br.com.imarui.identity.identity.core.domain.exceptions.affiliation;

public class AffiliationAlreadyInactiveException extends RuntimeException {

    public AffiliationAlreadyInactiveException(String affiliationId) {
        super("Affiliation is already inactive. affiliationId=" + affiliationId);
    }
}