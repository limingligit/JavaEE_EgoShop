package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChild;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;
@Service
public class TbItemParamServiceImpl implements TbItemParamService{

	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid dataGrid = tbItemParamDubboServiceImpl.showPage(page, rows);
		List<TbItemParam> list = (List<TbItemParam>) dataGrid.getRows();
		List<TbItemParamChild> list2=new ArrayList<>();
		for (TbItemParam tbItemParam : list) {
			TbItemParamChild tbItemParamChild = new TbItemParamChild();
			tbItemParamChild.setId(tbItemParam.getId());
			tbItemParamChild.setItemCatId(tbItemParam.getItemCatId());
			tbItemParamChild.setCreated(tbItemParam.getCreated());
			tbItemParamChild.setUpdated(tbItemParam.getUpdated());
			tbItemParamChild.setParamData(tbItemParam.getParamData());
			tbItemParamChild.setItemCatName(tbItemCatDubboServiceImpl.selById(tbItemParam.getItemCatId()).getName());
			list2.add(tbItemParamChild);
		}
		dataGrid.setRows(list2);
		return dataGrid;
	}
	@Override
	public int delete(String ids) throws Exception {
		return tbItemParamDubboServiceImpl.delByIds(ids);
	}
	@Override
	public EgoResult showParam(long catId) {
		EgoResult egoResult = new EgoResult();
		TbItemParam param = tbItemParamDubboServiceImpl.selByCatId(catId);
		if (param!=null) {
			egoResult.setStatus(200);
			egoResult.setData(param);
		}
		return egoResult;
	}
	@Override
	public EgoResult insertParam(TbItemParam param) {
		EgoResult egoResult = new EgoResult();
		Date date = new Date();
		param.setCreated(date);
		param.setUpdated(date);
		int index = tbItemParamDubboServiceImpl.insParm(param);
		if(index==1){
			egoResult.setStatus(200);
		}
		return egoResult;
	}

}
