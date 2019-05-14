import java.util.{Collections, Properties}

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

import scala.collection.JavaConversions._

object KafkaConsumerExample {

  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load(this.getClass().getClassLoader())
    val envProps = conf.getConfig(args(0))
    println(envProps)
    println(envProps.getString("topic"))
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, envProps.getString("bootstrap.server"))
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "1")

    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(Collections.singletonList(envProps.getString("topic")))
    while (true) {
      val records = consumer.poll(500)
      for (record <- records.iterator()) {
        println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
      }
    }
  }
}

/*
producer created
rajeshs_t1
null
ProducerRecord(topic=rajeshs_t1, partition=null, headers=RecordHeaders(headers = [], isReadOnly = false), key=hello, value=Universe, timestamp=null)
sent msg
closed producer

Process finished with exit code 0
 */