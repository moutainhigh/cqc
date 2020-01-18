package com.cqc.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UmsAdminLoginParam;
import com.cqc.admin.dto.UmsAdminParam;
import com.cqc.admin.service.UmsAdminService;
import com.cqc.common.api.Result;
import com.cqc.model.UmsAdmin;
import com.cqc.security.bean.AccessToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            Result.failed();
        }
        return Result.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<AccessToken> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        AccessToken token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        return Result.success(token);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public Result<AccessToken> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        AccessToken refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return Result.failed("token已经过期！");
        }
        return Result.success(refreshToken);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getAdminInfo(Principal principal) {
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", umsAdmin.getIcon());
        return Result.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Result logout() {
        return Result.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<UmsAdmin>> list(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsAdmin> adminList = adminService.list(name, pageSize, pageNum);
        return Result.success(adminList);
    }

    
}
