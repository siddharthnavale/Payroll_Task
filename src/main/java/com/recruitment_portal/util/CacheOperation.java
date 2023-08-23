package com.recruitment_portal.util;

import org.springframework.stereotype.Service;

import com.recruitment_portal.configuration.CacheConfig;

@Service
public class CacheOperation {

	public CacheOperation() {
		super();
	}

	public Boolean isKeyExist(String key1, String key2) {
		return CacheConfig.redisTemplate().opsForHash().hasKey(key1, key2);
	}

	public void addInCache(String key1, String key2, Object val) {
		CacheConfig.redisTemplate().opsForHash().put(key1, key2, val);
	}

	public Object getFromCache(String key1, String key2) {
		return CacheConfig.redisTemplate().opsForHash().get(key1, key2);
	}

	public void removeKeyFromCache(String key) {
		CacheConfig.redisTemplate().delete(key);
		return;
	}

	public void removeAllKeysStartingWith() {
		CacheConfig.redisConnectionFactory().getConnection().flushAll();

	}

}
