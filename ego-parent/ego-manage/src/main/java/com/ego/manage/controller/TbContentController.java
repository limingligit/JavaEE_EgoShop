package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;

@Controller
public class TbContentController {
	@Resource
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid show(long categoryId,int page,int rows){
		return tbContentServiceImpl.show(categoryId, page, rows);
	}
	
	@RequestMapping("content/save")
	@ResponseBody
	private EgoResult insert(TbContent content){
		return tbContentServiceImpl.insert(content);
	}

	@RequestMapping("rest/content/edit")
	@ResponseBody
	private EgoResult update(TbContent content){
		return tbContentServiceImpl.update(content);
	}
	
	@RequestMapping("content/delete")
	@ResponseBody
	private EgoResult delete(String ids){
		EgoResult egoResult = new EgoResult();
		try {
			int index = tbContentServiceImpl.delete(ids);
			if(index==1){
				egoResult.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			egoResult.setData(e.getMessage());
		}
		return egoResult;
	}
}
