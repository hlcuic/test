package com.hello.kafka.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class KafkaReceiver {

	private final Consumer<String, String> kafkaConsumer;

	public final static String TOPIC = "JAVA_TOPIC";

	private KafkaReceiver() {
		kafkaConsumer = createKafkaConsumer();
	}

	private Consumer<String, String> createKafkaConsumer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "127.0.0.1:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
		return kafkaConsumer;
	}

	private void consumer() {
		Collection<String> coll = new ArrayList<>();
		coll.add(TOPIC);
		kafkaConsumer.subscribe(coll, new ConsumerRebalanceListener(){

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				
			}

			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
				
			}
			
		});
	}

	public static void main(String[] args) {
		new KafkaReceiver().consumer();
	}
}