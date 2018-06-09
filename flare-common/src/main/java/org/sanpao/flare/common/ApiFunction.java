package org.sanpao.flare.common;

import java.util.function.Function;

import org.sanpao.flare.util.GenericTypeUtils;
import org.sanpao.flare.util.GsonUtils;

public interface ApiFunction<T> extends Function<String, ApiResult> {

	ApiResult execute(T payload);

	@SuppressWarnings("unchecked")
	default ApiResult apply(String payload) {
		Class<?> payloadType = GenericTypeUtils.getInterfaceGenricType(getClass());
		T t = (T) GsonUtils.create().fromJson(payload, payloadType);
		return execute(t);
	}

}
