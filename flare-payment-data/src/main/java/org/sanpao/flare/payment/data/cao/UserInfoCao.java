package org.sanpao.flare.payment.data.cao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.sanpao.flare.payment.data.CacheNames;
import org.sanpao.flare.payment.domain.entity.PaymentInfo;
import org.sanpao.flare.payment.domain.repository.PaymentInfoRepository;

@RepositoryConfig(cacheName = CacheNames.PAYMENT_INFO_CACHE_NAME)
public interface UserInfoCao extends IgniteRepository<PaymentInfo, Long>, PaymentInfoRepository {

}
