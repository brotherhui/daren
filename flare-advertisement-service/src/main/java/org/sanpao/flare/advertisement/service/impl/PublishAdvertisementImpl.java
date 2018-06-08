package org.sanpao.flare.advertisement.service.impl;

import org.sanpao.flare.advertisement.api.PublishAdvertisement;
import org.sanpao.flare.common.IgniteService;
import org.sanpao.flare.common.ApiResult;

public class PublishAdvertisementImpl extends IgniteService implements PublishAdvertisement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7266508676998125129L;

	@Override
	public ApiResult apply(String payload) {
		// TODO Auto-generated method stub
		logger.info("============>I Publish A Advertisement, Id Is {}", payload);
		return new ApiResult();
	}

}
