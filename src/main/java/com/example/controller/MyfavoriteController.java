package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.QueryPageParam;
import com.example.common.Result;
import com.example.entity.Myfavorite;
import com.example.entity.User;
import com.example.service.IMyfavoriteService;
import com.example.service.IUserService;
import com.example.service.IUsercarService;
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
 * @since 2023-06-15
 */
@RestController
@RequestMapping("/myfavorite")
public class MyfavoriteController {
    @Resource
    private IMyfavoriteService myfavoriteService;

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String id = (String) param.get("id");

        Page<Myfavorite> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Myfavorite> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(id) && !"null".equals(id)) {
            lambdaQueryWrapper.eq(Myfavorite::getId, id);
        }

        IPage result = myfavoriteService.page(page, lambdaQueryWrapper);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }

}
