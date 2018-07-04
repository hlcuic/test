package com.hello.kafka.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaReceiver {

	private final Consumer<String, String> kafkaConsumer;

	public final static String TOPIC = "JAVA_TOPIC";

	private KafkaReceiver() {
		kafkaConsumer = createKafkaConsumer();
	}

	private Consumer<String, String> createKafkaConsumer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "1");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("max.poll.records", 1000);
		props.put("auto.offset.reset", "earliest");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
		return kafkaConsumer;
	}

	private void consumer() {
		Collection<String> coll = new ArrayList<>();
		coll.add(TOPIC);
		kafkaConsumer.subscribe(coll);
		while (true) {
			//1次从kafka服务器拉取1000条数据
			ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
			for (Iterator<ConsumerRecord<String, String>> iter = records.iterator(); iter.hasNext();) {
				ConsumerRecord<String, String> record = iter.next();
				System.out.println(record.key() + " , " + record.value());
			}
		}
	}

	public static void main(String[] args) {
		new KafkaReceiver().consumer();
	}
}