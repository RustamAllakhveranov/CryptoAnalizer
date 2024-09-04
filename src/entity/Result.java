package entity;

public class Result {
    private String message;
    private ResultCode resultCode;

    public Result(String message, ResultCode resultCode) {
        this.message = message;
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    @Override
    public String toString() {
        return "Result: \"" + resultCode + "\". Message: \"" + message + "\"";
    }
}