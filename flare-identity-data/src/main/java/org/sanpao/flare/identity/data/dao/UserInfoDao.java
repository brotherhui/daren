package org.sanpao.flare.identity.data.dao;

import org.sanpao.flare.identity.domain.entity.UserInfo;
import org.sanpao.flare.identity.domain.repository.UserInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Long>, UserInfoRepository {

}
