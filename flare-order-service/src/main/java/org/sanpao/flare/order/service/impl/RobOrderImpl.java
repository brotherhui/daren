package org.sanpao.flare.order.service.impl;

import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.IgniteFunction;
import org.sanpao.flare.order.api.RobOrder;

public class RobOrderImpl extends IgniteFunction implements RobOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6968934278566594399L;

	@Override
	public ApiResult execute(ApiPayload payload) {
		// TODO Auto-generated method stub
		return new ApiResult(0, "msg", "{ data }");
	}

}
