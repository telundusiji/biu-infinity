package site.teamo.biu.infinity.connection.kafka280;

import org.apache.kafka.clients.admin.KafkaAdminClient;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;
import site.teamo.biu.infinity.connection.sdk.core.ResourceConnection;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/22 12:30
 * @Description:
 */
public class KafkaConnection extends ResourceConnection<KafkaResource> {

    private KafkaAdminClient client;

    public KafkaConnection(KafkaResource resource) {
        super(resource);
    }

    @Override
    public MetadataOperator getMetadataOperator() {
        return null;
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}
