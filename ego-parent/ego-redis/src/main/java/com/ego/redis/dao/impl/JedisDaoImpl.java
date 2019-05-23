package com.ego.redis.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ego.redis.dao.JedisDao;

import redis.clients.jedis.JedisCluster;

/** 
* @author 作者 : ZZG
* @version 创建时间：2019年5月23日 下午9:38:05 
*/
@Repository
public class JedisDaoImpl implements JedisDao{
	@Resource
	private JedisCluster jedisClients;
	
	@Override
	public Boolean exists(String key) {
		return jedisClients.exists(key);
	}

	@Override
	public Long del(String key) {
		return jedisClients.del(key);
	}

	@Override
	public String set(String key, String value) {
		return jedisClients.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisClients.get(key);
	}
	
}
