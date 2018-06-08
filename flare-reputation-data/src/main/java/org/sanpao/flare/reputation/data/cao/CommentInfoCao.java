package org.sanpao.flare.reputation.data.cao;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.sanpao.flare.reputation.data.CacheNames;
import org.sanpao.flare.reputation.domain.entity.CommentInfo;
import org.sanpao.flare.reputation.domain.repository.CommentInfoRepository;

@RepositoryConfig(cacheName = CacheNames.COMMENT_INFO_CACHE_NAME)
public interface CommentInfoCao extends IgniteRepository<CommentInfo, Long>, CommentInfoRepository {

}
