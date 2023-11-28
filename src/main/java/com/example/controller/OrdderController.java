package com.example.controller;


import com.example.common.Result;
import com.example.entity.Ordder;

import com.example.entity.User;
import com.example.service.IOrdderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ch
 * @since 2023-06-16
 */
@RestController
@RequestMapping("/ordder")
public class OrdderController {
    @Resource
    private IOrdderService ordderService;

    //删除
    @GetMapping("/delete")
    public Result del(@RequestParam String oid) {
        return ordderService.removeById(oid) ? Result.success() : Result.fail();
    }


    @PostMapping("/update")
    public Result update(@RequestBody Ordder ordder) {

        return ordderService.updateById(ordder) ? Result.success() : Result.fail();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Ordder ordder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        ordder.setDatetime(formattedDateTime);
        ordder.setEndtime("1");
        return ordderService.saveOrUpdate(ordder) ? Result.success() : Result.fail();
    }

}
