package live.anvill.ws.responses;

public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("missing required field, check the documentation for reference"),
    RECORD_ALREADY_EXIST("Record already exist"),
    INTERNAL_SERVER_ERROR("internal server error"),
    NO_RECORD_FOUND("No record avaliable for that search"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("could not update the recorc"),
    COULD_NOT_DELTE_RECORD("could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("email address could not be verified");

    private String errorMessage;
    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
