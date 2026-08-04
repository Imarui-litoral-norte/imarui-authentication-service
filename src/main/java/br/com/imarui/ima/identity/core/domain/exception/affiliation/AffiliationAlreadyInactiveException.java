package br.com.imarui.ima.identity.core.domain.exception.affiliation;

public class AffiliationAlreadyInactiveException extends RuntimeException {

    public AffiliationAlreadyInactiveException(String affiliationId) {
        super("Affiliation is already inactive. affiliationId=" + affiliationId);
    }
}