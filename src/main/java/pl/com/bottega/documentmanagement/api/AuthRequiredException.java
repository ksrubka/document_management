package pl.com.bottega.documentmanagement.api;

public class AuthRequiredException extends RuntimeException {

    public AuthRequiredException() {
        super("Authentication required but no user in current session");
    }

}
