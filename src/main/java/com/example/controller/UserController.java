package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.QueryPageParam;
import com.example.common.Result;
import com.example.entity.Menu;
import com.example.entity.User;
import com.example.service.IMenuService;
import com.example.service.IUserService;
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
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private IMenuService menuService;


    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List list = userService.lambdaQuery()
                .eq(User::getPhone, user.getPhone())
                .eq(User::getPassword, user.getPassword()).list();

        if (list.size()>0){
            User user1=(User)list.get(0);
            List menulist = menuService.lambdaQuery().like(Menu::getMenuright,user1.getRoleid()).list();
            HashMap res=new HashMap();
            res.put("user",user1);
            res.put("menu",menulist);
            return Result.success(res);
        }
        return Result.fail();
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam String id) {
        List list = userService.lambdaQuery().eq(User::getId, id).list();
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }
    @GetMapping("/findByPhone")
    public Result findByPhone(@RequestParam String phone) {
        List list = userService.lambdaQuery().eq(User::getPhone, phone).list();
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.success() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {

        return userService.updateById(user) ? Result.success() : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result del(@RequestParam String id) {
        return userService.removeById(id) ? Result.success() : Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user) {
        return userService.updateById(user);
    }

    //新增或修改
    @GetMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }


    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());
        }

        return Result.success(userService.list(lambdaQueryWrapper));
    }

    @GetMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");


        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, name);

    IPage result = userService.page(page, lambdaQueryWrapper);
        System.out.println("total==" + result.getTotal());
        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String roleid = (String) param.get("roleid");


        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
//        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
//            lambdaQueryWrapper.like(User::getName, name).or().like(User::getPhone, name);
//        }
        lambdaQueryWrapper.eq(User::getRoleid, roleid);
        lambdaQueryWrapper.and(wrapper -> {
            wrapper.like(User::getName, name).or().like(User::getPhone, name);
        });

        IPage result = userService.page(page, lambdaQueryWrapper);
        System.out.println("total==" + result.getTotal());
        return Result.success(result.getRecords(), result.getTotal());
    }

//    @PostMapping("/user_listPageC")
//    public Result user_listPageC(@RequestBody QueryPageParam query) {
//        HashMap param = query.getParam();
//        String name = (String) param.get("name");
//        String roleid = "2";
//
//        Page<User> page = new Page();
//        page.setCurrent(query.getPageNum());
//        page.setSize(query.getPageSize());
//
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.eq(User::getRoleid, roleid);
//        lambdaQueryWrapper.and(wrapper -> {
//            wrapper.like(User::getName, name).or().like(User::getPhone, name);
//        });
//
//
//        IPage result = userService.page(page, lambdaQueryWrapper);
//        System.out.println("total==" + result.getTotal());
//        return Result.success(result.getRecords(), result.getTotal());
//    }

}
