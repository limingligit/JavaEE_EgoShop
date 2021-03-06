package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

public interface TbItemParamDubboService {

	/**
	 * 分页查询数据
	 * @param page
	 * @param rows
	 * @return 包含当前页显示条数和总条数
	 */
	EasyUIDataGrid showPage(int page,int rows);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int delByIds(String ids) throws Exception ;
	
	/**
	 * 根据类目id查询规格参数
	 * @param catId
	 * @return
	 */
	TbItemParam selByCatId(long catId);
	/**
	 * 新增规格参数
	 * @param parm
	 * @return
	 */
	int insParm(TbItemParam param);
}
