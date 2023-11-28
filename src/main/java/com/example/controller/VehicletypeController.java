package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.QueryPageParam;
import com.example.common.Result;
import com.example.entity.User;
import com.example.entity.Vehicle;
import com.example.entity.Vehicletype;
import com.example.service.IVehicletypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ch
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/vehicletype")
public class VehicletypeController {

    @Resource
    private IVehicletypeService iVehicletypeService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Vehicletype vehicletype) {
        return iVehicletypeService.save(vehicletype) ? Result.success() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Vehicletype vehicletype) {
        return iVehicletypeService.updateById(vehicletype) ? Result.success() : Result.fail();
    }

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Page<Vehicletype> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Vehicletype> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper1.like(Vehicletype::getTypename, name).or().like(Vehicletype::getBrand, name).or().like(Vehicletype::getTypedescription, name);
        }
        IPage result = iVehicletypeService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }
}
