package org.sanpao.flare.identity.data.cao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.sanpao.flare.identity.data.CacheNames;
import org.sanpao.flare.identity.domain.entity.UserInfo;
import org.sanpao.flare.identity.domain.repository.UserInfoRepository;

@RepositoryConfig(cacheName = CacheNames.USER_INFO_CACHE_NAME)
public interface UserInfoCao extends IgniteRepository<UserInfo, Long>, UserInfoRepository {

}
