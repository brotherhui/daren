package org.sanpao.flare.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class IgniteFunctionProxy implements InitializingBean {

	final static Properties PROPERTIES = new Properties();

	final static Map<String, Class<ApiFunction<?>>> CACHE = new ConcurrentHashMap<String, Class<ApiFunction<?>>>();

	@Value("classpath:service-function.properties")
	private Resource resource;

	@Autowired
	private Ignite ignite;

	public ApiResult execute(String function, String payload) {
		Class<ApiFunction<?>> functionInterface = load(function);
		ApiFunction<?> fn = IgniteFunctionFactory.newFunction(ignite, functionInterface);
		return fn.apply(payload);
	}

	@SuppressWarnings("unchecked")
	public Class<ApiFunction<?>> load(String function) {
		Class<ApiFunction<?>> serviceClass = null;
		if (CACHE.containsKey(function)) {
			serviceClass = CACHE.get(function);
		} else {
			String serviceClassName = PROPERTIES.getProperty(function);
			try {
				serviceClass = (Class<ApiFunction<?>>) Class.forName(serviceClassName);
				CACHE.putIfAbsent(function, serviceClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return serviceClass;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		try {
			PROPERTIES.load(reader);
		} finally {
			reader.close();
		}
	}

}
