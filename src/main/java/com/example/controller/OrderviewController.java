package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.QueryPageParam;
import com.example.common.Result;
import com.example.entity.Orderview;
import com.example.entity.User;
import com.example.entity.Vehicle;
import com.example.service.IMenuService;
import com.example.service.IOrderviewService;
import com.example.service.IUserService;
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
 * @since 2023-06-16
 */
@RestController
@RequestMapping("/orderview")
public class OrderviewController {
    @Resource
    private IOrderviewService orderviewService;
    @Resource
    private IMenuService menuService;

    @PostMapping("/listPageEin")
    public Result listPageEin(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Integer isend = 2;
        Page<Orderview> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Orderview> lambdaQueryWrapper1 = new LambdaQueryWrapper();

        lambdaQueryWrapper1.eq(Orderview::getIsend,isend);
        lambdaQueryWrapper1.and(wrapper -> {
            wrapper.like(Orderview::getName, name).or().like(Orderview::getPhone, name);
        });

        IPage result = orderviewService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/listPageEout")
    public Result listPageE(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Integer isend = 1;
        Page<Orderview> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Orderview> lambdaQueryWrapper1 = new LambdaQueryWrapper();

        lambdaQueryWrapper1.eq(Orderview::getIsend,isend);
        lambdaQueryWrapper1.and(wrapper -> {
            wrapper.like(Orderview::getName, name).or().like(Orderview::getPhone, name);
        });

        IPage result = orderviewService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Page<Orderview> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Orderview> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper1.like(Orderview::getName, name);
        }
        IPage result = orderviewService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }
    @PostMapping("/listPageUser")
    public Result listPageUser(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String id = (String) param.get("id");
        Page<Orderview> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Orderview> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(id) && !"null".equals(id)) {
            lambdaQueryWrapper1.eq(Orderview::getId, id);
        }
        IPage result = orderviewService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }
}
