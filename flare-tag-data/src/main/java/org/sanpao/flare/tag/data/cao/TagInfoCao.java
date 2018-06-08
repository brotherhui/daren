package org.sanpao.flare.tag.data.cao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.sanpao.flare.tag.data.CacheNames;
import org.sanpao.flare.tag.domain.entity.TagInfo;
import org.sanpao.flare.tag.domain.repository.TagInfoRepository;

@RepositoryConfig(cacheName = CacheNames.TAG_INFO_CACHE_NAME)
public interface TagInfoCao extends IgniteRepository<TagInfo, Long>, TagInfoRepository {

}
