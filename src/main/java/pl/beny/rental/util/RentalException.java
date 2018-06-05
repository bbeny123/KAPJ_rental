package pl.beny.rental.util;

public class RentalException extends Exception {

    public enum RentalErrors {
        NOT_AUTHORIZED(1, "Not authorized", "error.authorized", null),
        CAPTCHA_ERROR(2, "Captcha Error", "error.captcha", null),
        USER_EXISTS(3, "The e-mail address is already in use", "error.user.exists", null),
        ITEM_NOT_EXISTS(4, "The item does not exist in database", "error.item.not.exists", null),
        USER_NOT_EXISTS(5, "The e-mail does not exist in database", "error.user.not.exists", "token"),
        TOKEN_NOT_EXISTS(6, "The token does not exist in database", "error.token.not.exists", null);

        private int code;
        private String message;
        private String source;
        private String viewName;

        RentalErrors(int code, String message, String source, String viewName) {
            this.code = code;
            this.message = message;
            this.source = source;
            this.viewName = viewName;
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

    public String getViewName() {
        return error.viewName;
    }

    @Override
    public String toString() {
        return error.code + ": " + error.message;
    }

}