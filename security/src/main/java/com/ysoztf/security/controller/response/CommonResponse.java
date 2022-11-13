package com.ysoztf.security.controller.response;

public class CommonResponse<R> {
    private String errorMessage = "";
    private long errorCode;
    private R result;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public static class CommonResponseBuilder<R> {
        private String errorMessage = "";
        private long errorCode;
        private R result;

        public static <R> CommonResponseBuilder getCommonResponseBuilder() {
            return new CommonResponseBuilder<R>();
        }

        public CommonResponseBuilder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public CommonResponseBuilder withErrorCode(long errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public CommonResponseBuilder withResult(R result) {
            this.result = result;
            return this;
        }

        public CommonResponseBuilder withCommonException(CommonException commonException) {
            this.errorCode = commonException.getCommonErrorCode().getErrorCode();
            this.errorMessage = commonException.getMessage();
            return this;
        }

        public CommonResponseBuilder withCommonErrorCode(CommonErrorCode commonErrorCode) {
            this.errorCode = commonErrorCode.getErrorCode();
            this.errorMessage = commonErrorCode.getErrorMessage();
            return this;
        }

        public CommonResponse<R> build() {
            CommonResponse<R> response = new CommonResponse<>();
            response.setErrorMessage(errorMessage);
            response.setErrorCode(errorCode);
            response.setResult(result);
            return response;
        }
    }
}
