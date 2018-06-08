package org.sanpao.flare.reputation.data.dao;

import org.sanpao.flare.reputation.domain.entity.CommentInfo;
import org.sanpao.flare.reputation.domain.repository.CommentInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentInfoDao extends JpaRepository<CommentInfo, Long>, CommentInfoRepository {

}
