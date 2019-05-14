import java.util
import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import scala.collection.JavaConverters._



object ConsumerDemo {


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
    //val brokerlist = "wn01.itversity.com:6667,wn02.itversity.com:6667,wn03.itversity.com:6667"
    val groupId = "my-third-application"
    val topic = "rajeshs_t1"

    import org.apache.kafka.clients.consumer.ConsumerConfig
    val properties = new Properties()
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getCanonicalName)
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getCanonicalName)
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    println(properties.toString)
    println("properties done")
    val consumer = new KafkaConsumer[String, String](properties)
//    consumer.subscribe(util.Collections.singletonList(topic))
    // subscribe consumer to our topic(s)
    consumer.subscribe(util.Arrays.asList(topic))
    println("")
    // poll for new data
    println("poll for new data")
    while (true) {
      println("before record")
      val record = consumer.poll(10L).asScala
      println("record.isEmpty = "+record.isEmpty)
      for (data <- record.iterator)
        println(data.value())
    }

  }


}
