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
import com.example.service.IUserService;
import com.example.service.IVehicleService;
import com.example.service.IVehicletypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ch
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Resource
    private IVehicleService iVehicleService;
    @Resource
    private IVehicletypeService iVehicletypeService;

    @PostMapping("/update")
    public Result update(@RequestBody Vehicle vehicle) {
        return iVehicleService.updateById(vehicle) ? Result.success() : Result.fail();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Vehicle vehicle) {
        return iVehicleService.save(vehicle) ? Result.success() : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result del(@RequestParam String vid) {
        return iVehicleService.removeById(vid) ? Result.success() : Result.fail();
    }

    //查询所有
    @GetMapping("/listPage")
    public List<Vehicle> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String vid = (String) param.get("vid");

        Page<Vehicle> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Vehicle> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Vehicle::getVid, vid);

        IPage result = iVehicleService.page(page, lambdaQueryWrapper);
        System.out.println("total==" + result.getTotal());
        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String vid = (String) param.get("vid");
        Page<Vehicle> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Vehicle> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(vid) && !"null".equals(vid)) {
            lambdaQueryWrapper1.like(Vehicle::getVid, vid);
        }
        IPage result = iVehicleService.page(page, lambdaQueryWrapper1);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }
}
