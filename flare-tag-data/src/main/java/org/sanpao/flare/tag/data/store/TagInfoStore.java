package org.sanpao.flare.tag.data.store;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.sanpao.flare.tag.data.dao.TagInfoDao;
import org.sanpao.flare.tag.domain.entity.TagInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagInfoStore extends CacheStoreAdapter<Object, TagInfo> {

	@Autowired
	private TagInfoDao dao;

	@Override
	public TagInfo load(Object key) throws CacheLoaderException {
		return dao.findOne((Long) key);
	}

	@Override
	public void write(Entry<? extends Object, ? extends TagInfo> entry) throws CacheWriterException {
		dao.save(entry.getValue());
	}

	@Override
	public void delete(Object key) throws CacheWriterException {
		dao.delete(load(key));
	}

}