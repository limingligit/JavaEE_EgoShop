package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemDubboServiceImpl implements TbItemDubboService{
	
	@Resource
	private TbItemMapper tbItemMapper;
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public EasyUIDataGrid show(int page, int rows) {
		
		PageHelper.startPage(page,rows);
		
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		
		PageInfo<TbItem> pi=new PageInfo<>(list);
		
		EasyUIDataGrid datagrid=new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}

	@Override
	public int updItemStatus(TbItem tbItem) {
		
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	@Override
	public int insTbItem(TbItem tbItem) {
		return tbItemMapper.insert(tbItem);
	}

	@Override
	public int insTbItemTbItemDesc(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tIPI) throws Exception {
		int index=0;
		
		try {
			index+=tbItemMapper.insertSelective(tbItem);
			index+=tbItemDescMapper.insertSelective(tbItemDesc);
			index+=tbItemParamItemMapper.insertSelective(tIPI);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(index==3){
			return 1;
		}else{
			throw new Exception("新增失败，数据回滚");
		}
	}

}
