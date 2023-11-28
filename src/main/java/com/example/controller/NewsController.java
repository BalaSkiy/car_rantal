package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.QueryPageParam;
import com.example.common.Result;
import com.example.entity.News;
import com.example.entity.User;
import com.example.service.IMenuService;
import com.example.service.INewsService;
import com.example.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ch
 * @since 2023-06-18
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    private INewsService iNewsService;
    @Resource
    private IMenuService menuService;



    //新增
    @PostMapping("/save")
    public Result save(@RequestBody News news) {
        return iNewsService.save(news) ? Result.success() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody News news) {

        return iNewsService.updateById(news) ? Result.success() : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result del(@RequestParam String newsid) {

        return iNewsService.removeById(newsid) ? Result.success() : Result.fail();
    }


    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Page<News> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<News> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(News::getNewsinfo, name);
        }
        IPage result = iNewsService.page(page, lambdaQueryWrapper);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }
}
