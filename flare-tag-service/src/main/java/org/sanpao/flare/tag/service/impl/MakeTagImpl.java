package org.sanpao.flare.tag.service.impl;

import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.ignite.IgniteFunction;
import org.sanpao.flare.tag.api.MakeTag;

public class MakeTagImpl extends IgniteFunction implements MakeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773938155879934378L;

	@Override
	public ApiResult execute(ApiPayload payload) {
		// TODO Auto-generated method stub
		return new ApiResult(0, "msg", "{ data }");
	}

}
