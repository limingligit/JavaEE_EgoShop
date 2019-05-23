package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbContentDubboServiceImpl implements TbContentDubboService{
	@Resource
	private TbContentMapper tbContentMapper;
	@Override
	public EasyUIDataGrid selContentByPage(long cateId, int page, int rows) {
		TbContentExample example = new TbContentExample();
		PageHelper.startPage(page, rows);
		
		if(cateId!=0){
			example.createCriteria().andCategoryIdEqualTo(cateId);
		}
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pageInfo.getList());
		dataGrid.setTotal(pageInfo.getTotal());
		return dataGrid;
	}
	@Override
	public int insContent(TbContent content) {
		return tbContentMapper.insertSelective(content);
	}
	@Override
	public int updContentById(TbContent content) {
		return tbContentMapper.updateByPrimaryKeySelective(content);
	}
	@Override
	public int delContentByIds(String ids) throws Exception {
			int index=0;
			String[] idsArr = ids.split(",");
			for (String id : idsArr) {
				index+=tbContentMapper.deleteByPrimaryKey(Long.parseLong(id));
			}
			if(index==idsArr.length){
				return 1;
			}else{
				throw new Exception("删除失败，数据可能不存在！");
			}
	}
	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
		
		TbContentExample example = new TbContentExample();
		if(isSort){
			example.setOrderByClause("updated desc");
		}
		if(count!=0){
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> pi = new PageInfo<>(list);
			return pi.getList();
		}else{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
		
		
	}

}
