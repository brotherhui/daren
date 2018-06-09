package org.sanpao.flare.api.gateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ignite.Ignite;
import org.sanpao.flare.common.ApiFunction;
import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.ignite.IgniteFunctionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FunctionProxy implements InitializingBean {

	final static Properties PROPERTIES = new Properties();

	final static Map<String, Class<ApiFunction<?>>> CACHE = new ConcurrentHashMap<String, Class<ApiFunction<?>>>();

	@Value("classpath:service-function.properties")
	private Resource resource;

	@Autowired
	private Ignite ignite;

	public ApiResult invoke(String functionName, String payload) {
		Class<ApiFunction<?>> functionInterface = load(functionName);
		ApiFunction<?> function = IgniteFunctionFactory.newFunction(ignite, functionInterface);
		return function.apply(payload);
	}

	@SuppressWarnings("unchecked")
	public Class<ApiFunction<?>> load(String functionName) {
		Class<ApiFunction<?>> serviceClass = null;
		if (CACHE.containsKey(functionName)) {
			serviceClass = CACHE.get(functionName);
		} else {
			String serviceClassName = PROPERTIES.getProperty(functionName);
			try {
				serviceClass = (Class<ApiFunction<?>>) Class.forName(serviceClassName);
				CACHE.putIfAbsent(functionName, serviceClass);
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
