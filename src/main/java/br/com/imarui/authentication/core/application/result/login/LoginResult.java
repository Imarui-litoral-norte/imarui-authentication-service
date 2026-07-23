package br.com.imarui.authentication.core.application.result.login;

public sealed interface LoginResult permits
        AuthenticatedLoginResult,
        PasswordChangeRequiredLoginResult {
}