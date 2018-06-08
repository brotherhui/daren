package org.sanpao.flare.advertisement.data.cao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.sanpao.flare.advertisement.data.CacheNames;
import org.sanpao.flare.advertisement.domain.entity.AdvertisementInfo;
import org.sanpao.flare.advertisement.domain.repository.AdvertisementInfoRepository;

@RepositoryConfig(cacheName = CacheNames.ADVERTISEMENT_INFO_CACHE_NAME)
public interface AdvertisementInfoCao extends IgniteRepository<AdvertisementInfo, Long>, AdvertisementInfoRepository {

}
