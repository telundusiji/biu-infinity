package site.teamo.biu.infinity.web.common.exception;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/6
 */
public class InfinityException extends RuntimeException{
    public InfinityException() {
        super();
    }

    public InfinityException(String message) {
        super(message);
    }

    public InfinityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfinityException(Throwable cause) {
        super(cause);
    }

    protected InfinityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
