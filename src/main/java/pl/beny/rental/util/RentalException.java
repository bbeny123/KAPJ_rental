package pl.beny.rental.util;

public class RentalException extends Exception {

    public enum RentalErrors {
        NOT_AUTHORIZED(1, "Not authorized", "error.authorized"),
        CAPTCHA_ERROR(2, "Captcha Error", "error.captcha"),
        USER_EXISTS(3, "The e-mail address is already in use", "error.user.exists"),
        TOKEN_NOT_EXISTS(4, "The token does not exist in database", "error.token.not.exists");

        private int code;
        private String message;
        private String source;

        RentalErrors(int code, String message, String source) {
            this.code = code;
            this.message = message;
            this.source = source;
        }
    }

    private final RentalErrors error;

    public RentalException(RentalErrors error) {
        super(error.message);
        this.error = error;
    }

    public int getErrorCode() {
        return error.code;
    }

    public String getMessageSource() {
        return error.source;
    }

    @Override
    public String toString() {
        return error.code + ": " + error.message;
    }

}