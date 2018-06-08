package org.sanpao.flare.reputation.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.sanpao.flare.reputation.data.dao.CommentInfoDao;
import org.sanpao.flare.reputation.domain.entity.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentInfoStore extends CacheStoreAdapter<Object, CommentInfo> {

	@Autowired
	private CommentInfoDao dao;

	@Override
	public CommentInfo load(Object key) throws CacheLoaderException {
		return dao.findOne((Long) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends CommentInfo> entry) throws CacheWriterException {
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		dao.delete(load(key));
	}

}