package site.teamo.biu.infinity.connection.sdk.exception;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 22:42
 * @Description:
 */
public class MetadataOperationException extends RuntimeException{
    public MetadataOperationException() {
        super();
    }

    public MetadataOperationException(String message) {
        super(message);
    }

    public MetadataOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetadataOperationException(Throwable cause) {
        super(cause);
    }

    protected MetadataOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
