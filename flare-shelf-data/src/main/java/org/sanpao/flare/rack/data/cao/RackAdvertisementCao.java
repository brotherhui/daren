package org.sanpao.flare.rack.data.cao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.sanpao.flare.rack.data.CacheNames;
import org.sanpao.flare.rack.domain.entity.RackAdvertisement;
import org.sanpao.flare.rack.domain.repository.RackAdvertisementRepository;

@RepositoryConfig(cacheName = CacheNames.RACK_ADVERTISEMENT_CACHE_NAME)
public interface RackAdvertisementCao extends IgniteRepository<RackAdvertisement, Long>, RackAdvertisementRepository {

}
