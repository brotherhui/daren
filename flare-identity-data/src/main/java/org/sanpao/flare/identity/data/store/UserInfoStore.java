package org.sanpao.flare.identity.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.sanpao.flare.identity.data.dao.UserInfoDao;
import org.sanpao.flare.identity.domain.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoStore extends CacheStoreAdapter<Object, UserInfo> {

	@Autowired
	private UserInfoDao dao;

	@Override
	public UserInfo load(Object key) throws CacheLoaderException {
		return dao.findOne((Long) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends UserInfo> entry) throws CacheWriterException {
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		dao.delete(load(key));
	}

}