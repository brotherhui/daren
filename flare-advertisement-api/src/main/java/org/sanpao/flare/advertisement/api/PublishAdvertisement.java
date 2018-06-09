package org.sanpao.flare.advertisement.api;

import org.sanpao.flare.advertisement.api.PublishAdvertisement.ApiPayload;
import org.sanpao.flare.common.ApiFunction;

public interface PublishAdvertisement extends ApiFunction<ApiPayload> {

	public static class ApiPayload {

		private Long advertisementId;

		public Long getAdvertisementId() {
			return advertisementId;
		}

		public void setAdvertisementId(Long advertisementId) {
			this.advertisementId = advertisementId;
		}

	}

}
