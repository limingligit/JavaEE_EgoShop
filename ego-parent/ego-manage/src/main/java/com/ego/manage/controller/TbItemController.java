package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;

@Controller
public class TbItemController {
	@Resource
	private TbItemService tbItemServiceImpl;
	
	/**
	 * 分页显示商品
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid show(int page,int rows){
		return tbItemServiceImpl.show(page, rows);
	}
	
	/**
	 * 显示商品修改界面
	 * @return
	 */
	@RequestMapping("rest/page/item-edit")
	public String showItemEdit(){
		return "item-edit";
	}
	
	/**
	 * 批量删除商品
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/delete")
	@ResponseBody
	public EgoResult delete(String ids){
		EgoResult egoResult = new EgoResult();
		int index = tbItemServiceImpl.update(ids, (byte)3);
		if(index==1){
			egoResult.setStatus(200);
		}
		return egoResult;
	}
	
	/**
	 * 批量上架商品
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelf(String ids){
		EgoResult egoResult = new EgoResult();
		int index = tbItemServiceImpl.update(ids, (byte)1);
		if(index==1){
			egoResult.setStatus(200);
		}
		return egoResult;
	}
	
	/**
	 * 批量下架商品
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/instock")
	@ResponseBody
	public EgoResult instock(String ids){
		EgoResult egoResult = new EgoResult();
		int index = tbItemServiceImpl.update(ids, (byte)2);
		if(index==1){
			egoResult.setStatus(200);
		}
		return egoResult;
	}
	
	@RequestMapping("item/save")
	@ResponseBody
	public EgoResult insert(TbItem item,String desc,String itemParams){
		EgoResult egoResult = new EgoResult();
		try {
			if(tbItemServiceImpl.save(item, desc,itemParams)==1){
				egoResult.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			egoResult.setData(e.getMessage());
		}
		return egoResult;
	}
}
