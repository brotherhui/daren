package org.sanpao.flare.tag.data.dao;

import org.sanpao.flare.tag.domain.entity.TagInfo;
import org.sanpao.flare.tag.domain.repository.TagInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagInfoDao extends JpaRepository<TagInfo, Long>, TagInfoRepository {

}
