package site.teamo.biu.infinity.common.exception;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/23
 */
public interface ErrorCode {

    int OK = 0;

    Integer code();

    String description();

    default InfinityRuntimeException createRuntimeException() {
        return new InfinityRuntimeException(this);
    }

    default InfinityRuntimeException createRuntimeException(String message) {
        return new InfinityRuntimeException(this, message);
    }

    default InfinityRuntimeException createRuntimeException(String message, Throwable e) {
        return new InfinityRuntimeException(this, message, e);
    }

    default InfinityException createException() {
        return new InfinityException(this);
    }

    default InfinityException createException(String message) {
        return new InfinityException(this, message);
    }

    default InfinityException createException(String message, Throwable e) {
        return new InfinityException(this, message, e);
    }

    enum BUSINESS implements ErrorCode {
        QUERY_ERROR(1001, "Null input parameter"),
        CREATE_ERROR(1002, "Null input parameter"),
        UPDATE_ERROR(1003, "Null input parameter");

        public final Integer code;
        public final String description;

        BUSINESS(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String description() {
            return description;
        }
    }

    enum PARAMETER implements ErrorCode {
        NULL_PARAMETER(2001, "Null input parameter"),
        MISSING_PARAMETER(2002, "Missing input parameter"),
        BAD_PARAMETER(2003, "Wrong parameter input");

        public final Integer code;
        public final String description;

        PARAMETER(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String description() {
            return description;
        }
    }

    enum RESOURCE implements ErrorCode {
        UNKNOWN_RESOURCE_ERROR(3001, "Unknown resource error"),

        RESOURCE_HAS_EXISTED(3002, "Resource already exists"),

        RESOURCE_NOT_EXISTS(3003, "The resource does not exist"),

        READ_RESOURCE_ERROR(3004, "Read resource error"),

        WRITE_RESOURCE_ERROR(3005, "Write resource error");

        public final Integer code;
        public final String description;

        RESOURCE(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String description() {
            return description;
        }
    }

}
