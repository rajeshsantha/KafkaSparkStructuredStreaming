    bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic twitter_tweets

    
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

