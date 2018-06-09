package org.sanpao.flare.payment.service.impl;

import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.ignite.IgniteFunction;
import org.sanpao.flare.payment.api.RewardOur;

public class RewardOurImpl extends IgniteFunction implements RewardOur {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5900669235881494349L;

	@Override
	public ApiResult execute(ApiPayload payload) {
		// TODO Auto-generated method stub
		return new ApiResult(0, "msg", "{ data }");
	}

}
