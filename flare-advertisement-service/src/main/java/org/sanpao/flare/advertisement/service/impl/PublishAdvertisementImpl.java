package org.sanpao.flare.advertisement.service.impl;

import org.sanpao.flare.advertisement.api.PublishAdvertisement;
import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.IgniteFunction;

public class PublishAdvertisementImpl extends IgniteFunction implements PublishAdvertisement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7266508676998125129L;

	@Override
	public ApiResult execute(ApiPayload payload) {
		// TODO Auto-generated method stub
		logger.info("============>I Publish A Advertisement, Id Is {}", payload);
		return new ApiResult(0, "msg", "{ data }");
	}

}
