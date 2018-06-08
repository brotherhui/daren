package org.sanpao.flare.order.data.cao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.sanpao.flare.order.data.CacheNames;
import org.sanpao.flare.order.domain.entity.OrderInfo;
import org.sanpao.flare.order.domain.repository.OrderInfoRepository;

@RepositoryConfig(cacheName = CacheNames.ORDER_INFO_CACHE_NAME)
public interface UserInfoCao extends IgniteRepository<OrderInfo, Long>, OrderInfoRepository {

}
