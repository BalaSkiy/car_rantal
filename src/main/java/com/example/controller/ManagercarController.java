package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.QueryPageParam;
import com.example.common.Result;
import com.example.entity.Managercar;
import com.example.entity.Vehicle;
import com.example.service.IManagercarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author ch
 * @since 2023-06-17
 */
@RestController
@RequestMapping("/managercar")
public class ManagercarController {
    @Resource
    private IManagercarService iManagercarService;


    @PostMapping("/listPageEout")
    public Result listPageE(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Page<Managercar> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Managercar> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper1.ne(Managercar::getRepair,name);
        }
        IPage result = iManagercarService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Page<Managercar> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Managercar> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper1.like(Managercar::getBrand, name).or().like(Managercar::getTypename, name).or().like(Managercar::getTypedescription, name);
        }
        IPage result = iManagercarService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }
}
