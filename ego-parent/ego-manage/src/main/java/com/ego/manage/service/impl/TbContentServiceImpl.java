package com.ego.manage.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;
@Service
public class TbContentServiceImpl implements TbContentService{
	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;
	@Override
	public EasyUIDataGrid show(long cateId, int page, int rows) {
		return tbContentDubboServiceImpl.selContentByPage(cateId, page, rows);
	}
	@Override
	public EgoResult insert(TbContent content) {
		EgoResult egoResult = new EgoResult();
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		int index = tbContentDubboServiceImpl.insContent(content);
		if(index>0){
			egoResult.setStatus(200);
		}
		return egoResult;
	}
	@Override
	public EgoResult update(TbContent content) {
		EgoResult egoResult = new EgoResult();
		Date date = new Date();
		content.setUpdated(date);
		int index = tbContentDubboServiceImpl.updContentById(content);
		if(index>0){
			egoResult.setStatus(200);
		}
		return egoResult;
	}
	@Override
	public int delete(String ids) throws Exception {
		int index = tbContentDubboServiceImpl.delContentByIds(ids);
		return index;
	}

}
