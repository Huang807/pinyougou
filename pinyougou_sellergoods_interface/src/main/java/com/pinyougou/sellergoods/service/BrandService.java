package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

public interface BrandService {
    public List<TbBrand>findAll();

    //数据分页显示
    public PageResult findPage(Integer pageNum,Integer pageSize);
    //商品增加
    public void add(TbBrand tbBrand);
    //商品修改
    public void update(TbBrand tbBrand);
    public TbBrand findOne(Long id);//根据ID查询需要修改商品的数据
    //商品删除
    public void delete(Long ids[]);

}
