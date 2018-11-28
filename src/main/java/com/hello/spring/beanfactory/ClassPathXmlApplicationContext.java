package com.hello.spring.beanfactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ClassPathXmlApplicationContext implements BeanFactory {
	
	private Map<String,Object> map = new HashMap<>();
	
	public ClassPathXmlApplicationContext(String fileName) throws DocumentException{
		init(fileName);
	}
	
	private void init(String fileName) throws DocumentException{
		SAXReader read = new SAXReader();
		ClassLoader classLoader = this.getClass().getClassLoader();
		Document doc = read.read(classLoader.getResourceAsStream(fileName));
		List<Element> elements = doc.selectNodes("/beans/bean");
		for(Element ele:elements){
			ele.attributeValue("id");
		}
	}

	@Override
	public Object getBean(String beanId) {
		return map.get(beanId);
	}

}
