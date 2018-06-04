/**
 * 
 */
package com.hello.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.hello.annotation.NotNull;
import com.hello.exception.CustBusinessException;

public class ValidateUtils {
	
	public static <T> void doValidator(T t){
		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			NotNull notNull = field.getDeclaredAnnotation(NotNull.class);
			if (null != notNull) {
				Object value = getValue(t, field.getName());
				if (!notNull(value)) {
					throwExcpetion(notNull.value());
				}
			}
		}
	}
	
	private static <T> Object getValue(T t,String name){
		String methodName = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
		try {
			Method method = t.getClass().getDeclaredMethod(methodName);
			return method.invoke(t,new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean notNull(Object value) {
		if (null == value) {
			return false;
		}
		if (value instanceof String && isEmpty((String) value)) {
			return false;
		}
		if (value instanceof List && isEmpty((List<?>) value)) {
			return false;
		}
		return null != value;
	}

	public static boolean isEmpty(String str) {
		return null == str || str.isEmpty();
	}

	public static boolean isEmpty(List<?> list) {
		return null == list || list.isEmpty();
	}

	public static void throwExcpetion(String msg) {
		if (null != msg) {
			throw new CustBusinessException(msg);
		}
	}

	public static void handlerExcpetion(Exception e) {
		if (e instanceof CustBusinessException) {
			System.out.println(((CustBusinessException) e).getMessage());
		} else {
			System.out.println("����ʧ��");
		}
	}
	
}
