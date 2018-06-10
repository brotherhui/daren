package org.sanpao.flare.common;

import java.util.Map;
import java.util.function.Function;

import org.sanpao.flare.util.GenericTypeUtils;
import org.sanpao.flare.util.GsonUtils;

public interface ApiFunction<T> extends Function<Map<String, Object>, ApiResult> {

	ApiResult execute(T payload);

	@SuppressWarnings("unchecked")
	default ApiResult apply(Map<String, Object> payload) {
		Class<?> functionInterface = getClass().getInterfaces()[0];
		Class<?> payloadType = GenericTypeUtils.getInterfaceGenricType(functionInterface);
		T apiPayload = (T) GsonUtils.create().fromJson(GsonUtils.create().toJson(payload), payloadType);
		return execute(apiPayload);
	}

}
