package org.sanpao.flare.identity.service.impl;

import org.sanpao.flare.common.ApiResult;
import org.sanpao.flare.common.ignite.IgniteFunction;
import org.sanpao.flare.identity.api.BindMobilePhone;

public class BindMobilePhoneImpl extends IgniteFunction implements BindMobilePhone {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8309110531037524915L;

	@Override
	public ApiResult execute(ApiPayload payload) {
		// TODO Auto-generated method stub
		return new ApiResult(0, "msg", "{ data }");
	}

}
