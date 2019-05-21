package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

public interface TbContentService {
	/**
	 * 分页查询
	 * @param cateId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(long cateId,int page,int rows);
	/**
	 * 添加
	 * @param content
	 * @return
	 */
	EgoResult insert(TbContent content);
	/**
	 * 修改
	 * @param content
	 * @return
	 */
	EgoResult update(TbContent content);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int delete(String ids) throws Exception;
}
