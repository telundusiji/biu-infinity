package site.teamo.biu.infinity.connection.sdk.exception;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 21:27
 * @Description:
 */
public class UnsupportedOperationException extends RuntimeException{
    public UnsupportedOperationException() {
        super();
    }

    public UnsupportedOperationException(String message) {
        super(message);
    }

    public UnsupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedOperationException(Throwable cause) {
        super(cause);
    }

    protected UnsupportedOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
