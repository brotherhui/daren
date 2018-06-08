package org.sanpao.flare.payment.data.dao;

import org.sanpao.flare.payment.domain.entity.PaymentInfo;
import org.sanpao.flare.payment.domain.repository.PaymentInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInfoDao extends JpaRepository<PaymentInfo, Long>, PaymentInfoRepository {

}
