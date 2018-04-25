package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;
    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }
    //商品分页显示
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        //设置分页的条件
        PageHelper.startPage(pageNum,pageSize);
        //紧跟着的第一个查询才会被分页
        List<TbBrand>tbBrands=brandMapper.selectByExample(null);
        Page<TbBrand>page=(Page<TbBrand>) tbBrands;
        return new PageResult(page.getTotal(),page.getResult());
    }
    //商品分页后增加
    @Override
    public void add(TbBrand tbBrand) {
        brandMapper.insert(tbBrand);
    }
    //商品修改
    @Override
    public void update(TbBrand tbBrand) {
        brandMapper.updateByPrimaryKey(tbBrand);
    }
    //商品修改,根绝id查询到需要修改的商品信息
    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {

        for (Long id:ids) {
            brandMapper.deleteByPrimaryKey(id);
        }            
        }

    @Override
    public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize) {
        //设置分页查询条件
        PageHelper.startPage(pageNum,pageSize);
        //紧跟着的第一个查询才会被分页
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria=example.createCriteria();
        if(brand!=null){
            //如果不等于空,添个查询条件
            if (brand.getName()!=null&&brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if (brand.getFirstChar()!=null&&brand.getFirstChar().length()>0){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        List<TbBrand> tbBrands = brandMapper.selectByExample(example);
        Page<TbBrand>page=(Page<TbBrand>) tbBrands;
        return new PageResult(page.getTotal(),page.getResult());
    }

}
