package org.sanpao.flare.api.gateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.apache.ignite.Ignite;
import org.sanpao.flare.common.IgniteServiceFactory;
import org.sanpao.flare.common.ApiResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ServiceMesh implements InitializingBean {

	final static Properties PROPERTIES = new Properties();

	final static Map<String, Class<Function<String, ApiResult>>> CACHE = new ConcurrentHashMap<String, Class<Function<String, ApiResult>>>();

	@Value("classpath:service-mesh.properties")
	private Resource resource;

	@Autowired
	private Ignite ignite;

	public ApiResult execute(String action, String payload) {
		Class<Function<String, ApiResult>> serviceClass = load(action);
		Function<String, ApiResult> fn = IgniteServiceFactory.newFunction(ignite, serviceClass);
		return fn.apply(payload);
	}

	@SuppressWarnings("unchecked")
	public Class<Function<String, ApiResult>> load(String action) {
		Class<Function<String, ApiResult>> serviceClass = null;
		if (CACHE.containsKey(action)) {
			serviceClass = CACHE.get(action);
		} else {
			String serviceClassName = PROPERTIES.getProperty(action);
			try {
				serviceClass = (Class<Function<String, ApiResult>>) Class.forName(serviceClassName);
				CACHE.putIfAbsent(action, serviceClass);
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
