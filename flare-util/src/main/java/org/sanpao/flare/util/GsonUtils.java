package org.sanpao.flare.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

/**
 * GSON工具类
 * @author 英博
 *
 */
public class GsonUtils {

	private final static Gson EXPOSE_GSON = GsonBuilderFactory.gsonBuilder(true).setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	private final static Gson UNEXPOSE_GSON = GsonBuilderFactory.gsonBuilder(false).setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	private final static Gson UNEXPOSE_LOWER_CASE_WITH_UNDERSCORES_GSON = GsonBuilderFactory.gsonBuilder(false).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static Gson createExpose() {
		return EXPOSE_GSON;
	}

	public static Gson create() {
		return UNEXPOSE_GSON;
	}

	public static Gson createLowerCaseWithUnderscores() {
		return UNEXPOSE_LOWER_CASE_WITH_UNDERSCORES_GSON;
	}

}
