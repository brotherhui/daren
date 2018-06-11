package org.sanpao.flare.rack.data.dao;

import org.sanpao.flare.rack.domain.entity.RackAdvertisement;
import org.sanpao.flare.rack.domain.repository.RackAdvertisementRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RackAdvertisementDao extends JpaRepository<RackAdvertisement, Long>, RackAdvertisementRepository {

}
