package com.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.portal.service.TbContentService;
import com.ego.redis.dao.JedisDao;

@Service
public class TbContentServiceImpl implements TbContentService{
	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.bigpic.key}")
	private String bigPic;
	
	@Override
	public String showBigPic() {
		if(jedisDaoImpl.exists(bigPic)){
			String redisResult = jedisDaoImpl.get(bigPic);
			if(redisResult!=null&&!"".equals(redisResult)){
				return redisResult;
			}
		}
		
		List<TbContent> list = tbContentDubboServiceImpl.selByCount(6, true);
		List<Map<String,Object>> jsonList=new ArrayList<>();
		for (TbContent tc : list) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("srcB", tc.getPic2());
			map.put("height", 240);
			map.put("alt", "加载失败");
			map.put("width", 670);
			map.put("src", tc.getPic());
			map.put("widthB", 550);
			map.put("href", tc.getUrl());
			jsonList.add(map);
		}
		String jsonS = JsonUtils.objectToJson(jsonList);
		jedisDaoImpl.set(bigPic, jsonS);
		return jsonS;
	}

}
