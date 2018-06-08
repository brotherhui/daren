package org.sanpao.flare.payment.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.sanpao.flare.payment.data.dao.PaymentInfoDao;
import org.sanpao.flare.payment.domain.entity.PaymentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentInfoStore extends CacheStoreAdapter<Object, PaymentInfo> {

	@Autowired
	private PaymentInfoDao dao;

	@Override
	public PaymentInfo load(Object key) throws CacheLoaderException {
		return dao.findOne((Long) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends PaymentInfo> entry) throws CacheWriterException {
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		dao.delete(load(key));
	}

}