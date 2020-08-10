package live.anvill.ws.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long SerialVersionUID = 1L;

    public UserServiceException(String errorMessage) {
            super(errorMessage);
    }
}
