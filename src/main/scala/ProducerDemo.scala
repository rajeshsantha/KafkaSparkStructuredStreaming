import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties
import org.apache.kafka.clients.producer.ProducerConfig


object ProducerDemo{


  def main (args: Array[String]): Unit = {

    /**
      * export KAFKA_HOME=/usr/hdp/current/kafka-broker
      * export PATH=$PATH:$KAFKA_HOME/bin
      * export zookeepers=nn01.itversity.com:2181,nn02.itversity.com:2181,rm01.itversity.com:2181
      * export brokerlist=wn01.itversity.com:6667,wn02.itversity.com:6667,wn03.itversity.com:6667
      * export bootstrapservers=wn01.itversity.com:6667,wn02.itversity.com:6667,wn03.itversity.com:6667
      */
    //val zookeepers = "nn01.itversity.com:2181,nn02.itversity.com:2181,rm01.itversity.com:2181"
    val bootstrapServers = "wn01.itversity.com:6667,wn02.itversity.com:6667,wn03.itversity.com:6667"
    val brokerlist = "wn01.itversity.com:6667,wn02.itversity.com:6667,wn03.itversity.com:6667"
    val groupId = "my-third-application"
    val topic = "rajeshs_t1"

    // create Producer properties
    val properties = new Properties()
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getCanonicalName)
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

    println("properties done")
    val producer = new KafkaProducer[String, String](properties)
    // create a producer record
    val record = new ProducerRecord[String, String](topic, "hello world")
    // poll for new data
    println("record topic"+record.topic()+"\n record value is "+record.value())
    producer.send(record)
    producer.flush()
    producer.close()
    println("producer done")

  }




}
