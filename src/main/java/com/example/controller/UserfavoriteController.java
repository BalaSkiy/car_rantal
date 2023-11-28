package com.example.controller;


import com.example.common.Result;
import com.example.entity.Userfavorite;
import com.example.service.IUserfavoriteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ch
 * @since 2023-06-15
 */
@RestController
@RequestMapping("/userfavorite")
public class UserfavoriteController {
    @Resource
    private IUserfavoriteService userfavoriteService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Userfavorite userfavorite) {

        return userfavoriteService.save(userfavorite) ? Result.success() : Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result del(@RequestParam String cid) {
        return userfavoriteService.removeById(cid) ? Result.success() : Result.fail();
    }

}
