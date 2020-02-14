package com.example.medicalsystem.user.controller;

import com.example.medicalsystem.common.ApiResponse;
import com.example.medicalsystem.user.repository.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.medicalsystem.user.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 注册
     * @param userId    id
     * @param password 密码
     * @param userName 昵称
     * @param gender 性别
     * @param email 邮箱
     * @param userTypeId 用户类型：1：普通用户 2：管理员
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ApiResponse register(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "gender") Integer gender,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "userTypeId") String userTypeId
    ){
        try{
            userService.register(userId,password,userName,gender,email,userTypeId);
        }catch (Exception e){
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success();
    }

    /**
     * 登陆
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ApiResponse login(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password
    ){
        User user = new User();
        try{
          user = userService.login(userId,password);
        }catch (Exception e){
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(user);
    }

    /**
     * 用户信息的展示
     * @param token
     * @return
     */
    @RequestMapping(value = "infoDetail",method = RequestMethod.POST)
    public ApiResponse infoDetail(
            @RequestParam(value = "token") String token
    ){
        User user = new User();
        try{
            user = userService.infoDetail(token);
        }catch (Exception e){
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(user);
    }

    /**
     * 完善个人信息
     * @param token
     * @param workUnit
     * @param jobNumber
     * @param age
     * @param phoneNumber
     * @param position
     * @param department
     * @return
     */
    @RequestMapping(value = "perfectInfo",method = RequestMethod.POST)
    public ApiResponse perfectInfo(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "workUnit") String workUnit,
            @RequestParam(value = "jobNumber") String jobNumber,
            @RequestParam(value = "age") Integer age,
            @RequestParam(value = "phoneNumber") Long phoneNumber,
            @RequestParam(value = "position") String position,
            @RequestParam(value = "department") String department
    ){
        User user = new User();
        try {
            user = userService.perfectInfo(token,workUnit,jobNumber,age,phoneNumber,position,department);
        }catch (Exception e){
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(user);
    }

    /**
     * 修改信息
     * @param token
     * @param newWorkUnit
     * @param newJobNumber
     * @param newAge
     * @param newPhoneNumber
     * @param newPosition
     * @param newDepartment
     * @param newEmail
     * @return
     */
    @RequestMapping(value = "changeInfo",method = RequestMethod.POST)
    public ApiResponse changeInfo(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "newWorkUnit") String newWorkUnit,
            @RequestParam(value = "newJobNumber") String newJobNumber,
            @RequestParam(value = "newAge") Integer newAge,
            @RequestParam(value = "newPhoneNumber") Long newPhoneNumber,
            @RequestParam(value = "newPosition") String newPosition,
            @RequestParam(value = "newDepartment") String newDepartment,
            @RequestParam(value = "newEmail") String newEmail
    ){
        User user = new User();
        try{
            user = userService.changeInfo(token,newWorkUnit,newJobNumber,newAge,newPhoneNumber,newPosition,newDepartment,newEmail);
        }catch (Exception e){
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(user);
    }
    /**
     * 修改密码
     * @param token
     * @param newPassword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "changePassword",method = RequestMethod.POST)
    public ApiResponse changePassword(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "newPassword") String newPassword
    ) throws Exception {

        return ApiResponse.success(userService.changePassword(token,newPassword));
    }
}
