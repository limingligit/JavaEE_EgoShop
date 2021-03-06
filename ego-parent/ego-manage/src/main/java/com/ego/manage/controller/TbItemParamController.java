package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Controller
public class TbItemParamController {
	@Resource
	private TbItemParamService tbItemParamServiceImpl;
	
	/**
	 * 规格参数 分页显示
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid show(int page,int rows){
		return tbItemParamServiceImpl.showPage(page, rows);
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult delete(String ids){
		EgoResult egoResult = new EgoResult();
		try {
			int index = tbItemParamServiceImpl.delete(ids);
			if(index==1){
				egoResult.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			egoResult.setData(e.getMessage());
		}
		return egoResult;
	}
	/**
	 * 根据类目id显示模板
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public EgoResult show(@PathVariable long catId){
		return tbItemParamServiceImpl.showParam(catId);
	}
	
	/**
	 * 根据类目id新增模板
	 * @param param
	 * @return
	 */
	@RequestMapping("item/param/save/{catId}")
	@ResponseBody
	public EgoResult insert(TbItemParam param,@PathVariable long catId){
		param.setItemCatId(catId);
		return tbItemParamServiceImpl.insertParam(param);
	}
}
