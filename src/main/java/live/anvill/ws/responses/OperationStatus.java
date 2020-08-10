package live.anvill.ws.responses;

public class OperationStatus {
    private String operationResult;
    private String operationName;

    public OperationStatus() {
    }

    public OperationStatus(String operationResult, String operationName) {
        this.operationResult = operationResult;
        this.operationName = operationName;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
