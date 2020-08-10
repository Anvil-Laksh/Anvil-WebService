package live.anvill.ws.exceptions;

import java.util.Date;

public class ErrorLog {
    private Date timeStamp;
    private String errorDescription;

    public ErrorLog(Date timeStamp, String errorDescription) {
        this.timeStamp = timeStamp;
        this.errorDescription = errorDescription;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
