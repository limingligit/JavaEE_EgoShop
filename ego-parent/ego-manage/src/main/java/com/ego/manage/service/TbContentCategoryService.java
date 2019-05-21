package com.ego.manage.service;

import java.util.List;

import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryService {
	/**
	 * 查询所有类目并转换为easyUI tree
	 * @return
	 */
	List<EasyUITree> showCategory(long id);
	/**
	 * 类目新增
	 * @param cate
	 * @return
	 */
	EgoResult create(TbContentCategory cate);
	/**
	 * 重命名
	 * @param cate
	 * @return
	 */
	EgoResult rename(TbContentCategory cate);
	/**
	 * 删除
	 * @param cate
	 * @return
	 */
	EgoResult delete(TbContentCategory cate);
}