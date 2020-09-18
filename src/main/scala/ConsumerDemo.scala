package com.example


import java.text.SimpleDateFormat
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

import scala.collection.JavaConversions._
import org.apache.kafka.common.TopicPartition
import java.util

object ConsumerDemo {
  val dateFormat = "yyyy-MM-dd HH:mm:ss"
  val epochToDate: Long => String = new SimpleDateFormat(dateFormat).format(_)

  def main(args: Array[String]): Unit = {

    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumergroup1_rajesh")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
    val consumer = new KafkaConsumer[String, String](props)
    // consumer.subscribe(Collections.singletonList(envProps.getString("topic")))
    // consumer.subscribe(Collections.singletonList("rajeshs_t1"))
    val tp = new TopicPartition("Testtopic1", 0)
    val tps = util.Arrays.asList(tp)
    consumer.assign(tps)
    consumer.seekToBeginning(tps)
    while (true) {
      val records = consumer.poll(500)
      for (record <- records.iterator()) {
        println("received metadata : \n " +
          "Topic      : " + record.topic() + "\n" +
          "Partiton   : " + record.partition() + "\n" +
          "Offset     : " + record.offset() + "\n" +
          "TimeStamp  : " + record.timestamp() + "\n" +
          "Date  : " + epochToDate(record.timestamp()) + "\n" +
          "Value      : " + record.value())
      }
    }
  }
}
