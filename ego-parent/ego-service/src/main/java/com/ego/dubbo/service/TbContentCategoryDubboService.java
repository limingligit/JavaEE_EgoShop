package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	/**
	 * 根据父id查询所有子类目
	 * @param id
	 * @return
	 */
	List<TbContentCategory> selByPid(long id);
	
	/**
	 * 新增
	 * @param cate
	 * @return
	 */
	int insTbContentCategory(TbContentCategory cate);
	
	/**
	 * 修改Category属性
	 *
	 * @return
	 */
	int updCategoryById(TbContentCategory cate);
	
	/**
	 * 根据id获取信息
	 * @param id
	 * @return
	 */
	TbContentCategory selCategoryById(long id);
}
