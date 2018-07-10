//package com.hello.kafka.consumer;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.Properties;
//
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.ByteArrayDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//import com.hlcui.model.Person;
//
//public class KafkaReceiver {
//
//	private final Consumer<String, Object> kafkaConsumer;
//
//	public final static String TOPIC = "JAVA_TOPIC";
//
//	private KafkaReceiver() {
//		kafkaConsumer = createKafkaConsumer();
//	}
//
//	private Consumer<String, Object> createKafkaConsumer() {
//		Properties props = new Properties();
//		//kafka集群服务列表
//		props.put("bootstrap.servers", "localhost:9092");
//		//分组，不同的分组可以重复消费同一个topic的数据
//		props.put("group.id", "1");
//		props.put("enable.auto.commit", "true");
//		props.put("auto.commit.interval.ms", "1000");
//		props.put("session.timeout.ms", "30000");
//		//从kafka服务器每次拉取数据最大量
//		props.put("max.poll.records", 1000);
//		props.put("auto.offset.reset", "earliest");
//		//反序列化key格式
//		props.put("key.deserializer", StringDeserializer.class.getName());
//		//反序列化value格式
//		props.put("value.deserializer", ByteArrayDeserializer.class.getName());
//		Consumer<String, Object> kafkaConsumer = new KafkaConsumer<>(props);
//		return kafkaConsumer;
//	}
//
//	private void consumer() throws JsonSyntaxException, UnsupportedEncodingException {
//		Gson gson = new Gson();
//		Collection<String> coll = new ArrayList<>();
//		coll.add(TOPIC);
//		kafkaConsumer.subscribe(coll);
//		while (true) {
//			//1次从kafka服务器拉取1000条数据
//			ConsumerRecords<String, Object> records = kafkaConsumer.poll(1000);
//			for (Iterator<ConsumerRecord<String, Object>> iter = records.iterator(); iter.hasNext();) {
//				ConsumerRecord<String, Object> record = iter.next();
//				byte[] data = (byte[])record.value();
//				String ss = new String(data,"UTF-8");
//				Person person = gson.fromJson(ss,Person.class);
//				System.out.println(person);
//				//System.out.println(record.key() + " , " + record.value());
//			}
//		}
//	}
//	
//	public static void main(String[] args) throws JsonSyntaxException, UnsupportedEncodingException {
//		new KafkaReceiver().consumer();
//	}
//}