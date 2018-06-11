package org.sanpao.flare.rack.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.sanpao.flare.rack.data.dao.RackAdvertisementDao;
import org.sanpao.flare.rack.domain.entity.RackAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RackAdvertisementStore extends CacheStoreAdapter<Object, RackAdvertisement> {

	@Autowired
	private RackAdvertisementDao dao;

	@Override
	public RackAdvertisement load(Object key) throws CacheLoaderException {
		return dao.findOne((Long) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends RackAdvertisement> entry) throws CacheWriterException {
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		dao.delete(load(key));
	}

}