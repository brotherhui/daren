package org.sanpao.flare.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericTypeUtils {

	final static Map<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<String, Class<?>>();

	final static Map<String, Class<?>> INTERFACE_CACHE = new ConcurrentHashMap<String, Class<?>>();

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {
		if (CLASS_CACHE.containsKey(clazz.getName())) {
			return CLASS_CACHE.get(clazz.getName());
		}

		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		Class<?> genPparameType = (Class) params[index];
		CLASS_CACHE.putIfAbsent(clazz.getName(), genPparameType);
		return genPparameType;
	}

	@SuppressWarnings("rawtypes")
	public static Class getInterfaceGenricType(Class clazz) {
		return getInterfaceGenricType(clazz, 0);
	}

	@SuppressWarnings("rawtypes")
	public static Class getInterfaceGenricType(Class clazz, int index) throws IndexOutOfBoundsException {
		if (INTERFACE_CACHE.containsKey(clazz.getName())) {
			return INTERFACE_CACHE.get(clazz.getName());
		}

		Type genType = clazz.getGenericInterfaces()[0];
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		Class<?> genPparameType = (Class) params[index];
		INTERFACE_CACHE.putIfAbsent(clazz.getName(), genPparameType);
		return genPparameType;
	}

}
