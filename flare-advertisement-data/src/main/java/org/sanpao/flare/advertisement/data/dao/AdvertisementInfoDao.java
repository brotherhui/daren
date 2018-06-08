package org.sanpao.flare.advertisement.data.dao;

import org.sanpao.flare.advertisement.domain.entity.AdvertisementInfo;
import org.sanpao.flare.advertisement.domain.repository.AdvertisementInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementInfoDao extends JpaRepository<AdvertisementInfo, Long>, AdvertisementInfoRepository {

}
