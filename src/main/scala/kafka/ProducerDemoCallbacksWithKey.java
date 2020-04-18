package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoCallbacksWithKey {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final Logger logger = LoggerFactory.getLogger(ProducerDemoCallbacksWithKey.class);
        String bootstrapServers = "localhost:9092";

//        String groupId = "my-third-application";
        String topic = "FirstTopic1";

        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        logger.info("Properties set");

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        // create kafka record as key value pair.
        for (int i = 1000; i < 1010; i++) {

            final String key = "id_" + i;
            String value = "value messages " + i + " from IDE";

            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
            logger.info("key        : " + key);
            // send the record to kafka as key value pair

            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // executes everytime record sent.
                    if (e == null) {
/*
                        logger.info("received metadata : \n " +
                                "Topic      : " + recordMetadata.topic() + "\n" +
                                "Partiton   : " + recordMetadata.partition() + "\n" +
                                "Offset     : " + recordMetadata.offset() + "\n" +
                                "TimeStamp  : " + recordMetadata.timestamp() + "\n");
*/
                        logger.info("key " + key + " stored in " + recordMetadata.partition() + " partiton \n");

                    } else {
                        logger.error("exception while sending the record");

                    }
                }
            }).get(); // added .get to make it synchronus
            logger.info("record sent");
        }
        producer.flush();
        producer.close();
        logger.info("The end");
    }
}
