    bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic twitter_tweets

    bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic twitter_status_connect
    bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic twitter_deletes_connect
    
start-zookeeper:

   bin/zookeeper-server-start.sh config/zookeeper.properties

    
start-kafka:
    bin/kafka-server-start.sh config/server.properties

    
    
create-topic:
    
    bin/kafka-topics.sh --create  --zookeeper localhost:2181 --replication-factor 1 --partitions 13 --topic my-topic

    
list-topics:

    bin/kafka-topics.sh --list --zookeeper localhost:2181

producer-console: 
    bin/kafka-console-producer.sh --broker-list localhost:9092 --topic my-topic

    
consumer-console:

    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic twitter_tweets --from-beginning   
    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic twitter_status_connect --from-beginning
#to run kafka connect for twitter
cd /home/rajesh/IdeaProjects/KafkaSparkStructuredStreaming/kafka-connect
connect-standalone.sh  connect-standalone.properties twitter.properties
