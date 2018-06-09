package org.sanpao.flare.reputation.service.impl;

import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.ignite.IgniteFunction;
import org.sanpao.flare.reputation.api.PublishBadReview;

public class PublishBadReviewImpl extends IgniteFunction implements PublishBadReview {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7395017322027217987L;

	@Override
	public ApiResult execute(ApiPayload payload) {
		// TODO Auto-generated method stub
		return new ApiResult(0, "msg", "{ data }");
	}

}
