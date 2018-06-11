package org.sanpao.flare.rack.api;

import org.sanpao.flare.common.ApiFunction;
import org.sanpao.flare.rack.api.AddAdvertisementToShelf.ApiPayload;

public interface AddAdvertisementToShelf extends ApiFunction<ApiPayload> {

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
