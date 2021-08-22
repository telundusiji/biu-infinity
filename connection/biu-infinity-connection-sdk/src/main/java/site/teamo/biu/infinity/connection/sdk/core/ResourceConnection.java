package site.teamo.biu.infinity.connection.sdk.core;

/**
 * @author haocongshun
 * @date 2021/8/20 16:20
 */
public abstract class ResourceConnection<R extends Resource> implements AutoCloseable {

    protected R resource;

    public volatile boolean isOpen;

    public ResourceConnection(R resource) {
        this.resource = resource;
        isOpen = true;
    }

    public abstract MetadataOperator getMetadataOperator();

    public <T> T getMetadataOperator(Class<T> tClass) {
        return tClass.cast(getMetadataOperator());
    }

    public R getResource() {
        return this.resource;
    }
}
