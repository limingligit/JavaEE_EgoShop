package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{

	@Resource
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pageInfo.getList());
		dataGrid.setTotal(pageInfo.getTotal());
		
		return dataGrid;
	}

	@Override
	public int delByIds(String ids) throws Exception {
		int index=0;
		String[] idsArr = ids.split(",");
		for (String id : idsArr) {
			index+=tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if(index==idsArr.length){
			return 1;
		}else{
			throw new Exception("删除失败，数据可能不存在！");
		}
	}

	@Override
	public TbItemParam selByCatId(long catId) {
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int insParm(TbItemParam param) {
		return tbItemParamMapper.insertSelective(param);
	}

}
