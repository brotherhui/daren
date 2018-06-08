package org.sanpao.flare.order.data.dao;

import org.sanpao.flare.order.domain.entity.OrderInfo;
import org.sanpao.flare.order.domain.repository.OrderInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoDao extends JpaRepository<OrderInfo, Long>, OrderInfoRepository {

}
