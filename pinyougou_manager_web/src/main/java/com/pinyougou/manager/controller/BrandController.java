package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        return brandService.findPage(pageNum, pageSize);
    }

    //商品增加
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand) {
        try {
            brandService.add(tbBrand);
            return new Result(true, "增加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败!");
        }
    }
    //修改商品
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand) {
        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败!");
        }
    }
    //根据商品id,找到需要修改的商品信息
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    @RequestMapping("/delete")
    public Result delete(Long ids[]){
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }
}
