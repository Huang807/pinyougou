package com.pinyougou.sellergoods.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.pojogroup.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Autowired
	private TbSpecificationOptionMapper optionMapper;
	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		//1.插入规格表
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.insert(tbSpecification);
		//2.获取到插入的规格表中的主键的ID 作为规格选项表的外键

		//3.插入规格选项表 需要规格Id
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		for (TbSpecificationOption option : specificationOptionList) {
			option.setSpecId(tbSpecification.getId());//设置规格的ID
			optionMapper.insert(option);
		}
	}


	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		//要更新
		//1.更新规格表
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		//2.更新规格对应的选项表
		//2.1 要先删除原来的选项列表
		TbSpecificationOptionExample exmaple = new TbSpecificationOptionExample();
		exmaple.createCriteria().andSpecIdEqualTo(tbSpecification.getId());
		optionMapper.deleteByExample(exmaple);//delete from option where spec_id=id

		//2.2 再插入从页面传递过来的选项列表
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		for (TbSpecificationOption option : specificationOptionList) {
			option.setSpecId(tbSpecification.getId());
			optionMapper.insert(option);
		}
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		//构造规格的选项的列表
		TbSpecificationOptionExample exmaple = new TbSpecificationOptionExample();
		//根据规格id查询
		exmaple.createCriteria().andSpecIdEqualTo(id);
		List<TbSpecificationOption> optionList = optionMapper.selectByExample(exmaple);//sleec  * from option where specId=id

		Specification specification = new Specification();
		specification.setSpecification(tbSpecification);
		specification.setSpecificationOptionList(optionList);
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdEqualTo(id);
			optionMapper.deleteByExample(example);
		}
	}


	@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();

		if(specification!=null){
			if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}

		}

		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		List<TbSpecification> specifications = specificationMapper.selectByExample(null);
		List<Map> specList = new ArrayList<>();
		for (TbSpecification specification : specifications) {
			Map map = new HashMap();
			map.put("id",specification.getId());
			map.put("text",specification.getSpecName());
			specList.add(map);
		}
		return specList;
	}

}
