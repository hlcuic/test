
package com.hello.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class CommonUtils {

	public static <T> Object getValue(T t, String fieldName) {
		Object value = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : props) {
				if (fieldName.equals(property.getName())) {
					Method method = property.getReadMethod();
					value = method.invoke(t, new Object[] {});
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static <T> void copyProperties(T t1, T t2) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(t2.getClass());
		PropertyDescriptor[] propDesc = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor descriptor : propDesc) {
			if ("class".equals(descriptor.getName())) {
				continue;
			}
			Method read = descriptor.getReadMethod();
			Method write = descriptor.getWriteMethod();
			Object value = read.invoke(t2, new Object[] {});
			if (null != value && null!=write) {
				write.invoke(t1, value);
			}
		}
	}

}
