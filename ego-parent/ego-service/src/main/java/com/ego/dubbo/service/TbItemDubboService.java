package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

public interface TbItemDubboService {
	/**
	 * 商品分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(int page,int rows);
	/**
	 * 修改商品状态
	 * @param tbItem
	 * @return
	 */
	int updItemStatus(TbItem tbItem);
	/**
	 * 商品新增
	 * @param tbItem
	 * @return
	 */
	int insTbItem(TbItem tbItem);
	/**
	 * 同时新增商品和商品描述
	 * @param tbItem
	 * @param tbItemDesc
	 * @return
	 */
	int insTbItemTbItemDesc(TbItem tbItem,TbItemDesc tbItemDesc,TbItemParamItem tIPI)throws Exception;
	
}
