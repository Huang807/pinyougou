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
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }
    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNum,Integer pageSize){
    return brandService.findPage(pageNum,pageSize);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"增加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"增加失败!");
        }
    }
}
