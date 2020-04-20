package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemoJava {
    public static void main(String[] args) {
        System.out.println("hello world");
        String bootstrapServers = "localhost:9092";

        String groupId = "my-third-application";
        String topic = "FirstTopic1";

        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
/*
    val producer = new KafkaProducer[String, String](properties)
    // create a producer record
    val record = new ProducerRecord[String, String](topic, "hello world")
    // poll for new data
    println("record topic"+record.topic()+"\n record value is "+record.value())
    producer.send(record)
    producer.flush()
    producer.close()
    println("producer done")

 */
        System.out.println("Properties set");

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        // create kafka record as key value pair.
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("firstTopic", "Hello from IDE");

        // send the record to kafka as key value pair
        producer.send(record);
        System.out.println("record sent");

        producer.flush();
        producer.close();
        System.out.println("The end");

    }


}
