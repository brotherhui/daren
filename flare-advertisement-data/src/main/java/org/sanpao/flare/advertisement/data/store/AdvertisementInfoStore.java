package org.sanpao.flare.advertisement.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.sanpao.flare.advertisement.data.dao.AdvertisementInfoDao;
import org.sanpao.flare.advertisement.domain.entity.AdvertisementInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementInfoStore extends CacheStoreAdapter<Object, AdvertisementInfo> {

	@Autowired
	private AdvertisementInfoDao dao;

	@Override
	public AdvertisementInfo load(Object key) throws CacheLoaderException {
		return dao.findOne((Long) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends AdvertisementInfo> entry) throws CacheWriterException {
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		dao.delete(load(key));
	}

}