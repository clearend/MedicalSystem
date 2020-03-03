package com.example.medicalsystem.user.service;

import com.example.medicalsystem.common.ApiResponse;
import org.springframework.stereotype.Service;
import com.example.medicalsystem.user.repository.User;
import com.example.medicalsystem.user.repository.UserRepository;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserRepository userRepository;

    /**
     * 用户注册
     * @param userId
     * @param password
     * @param userName
     */
    public void register(String userId, String password, String userName,Integer gender,String email,String userTypeId) throws Exception {
        if(checkUserIdIsExist(userId)){
            throw new Exception("用户名已存在");
        }
        try{
            User user = new User();
            user.setUserId(userId);
            user.setUserTypeId(userTypeId);
            user.setPassword(password);
            user.setUserName(userName);
            user.setGender(gender);
            user.setEmail(email);
            user.setCreateTime(System.currentTimeMillis());
            user.setUpdateTime(System.currentTimeMillis());
            userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("注册失败");
        }
    }

    /**
     * 检查用户名是否存在
     * @param userId
     * @return
     */
    public boolean checkUserIdIsExist(String userId){
        User user = userRepository.findByUserId(userId);
        if(user==null)
        {
            return false;
        }
        return true;
    }

    /**
     * 登陆
     * @param userId
     * @param password
     * @return  user
     * @throws Exception
     */
    public User login(String userId, String password) throws Exception {
        User user = userRepository.findByUserIdAndPassword(userId,password);
        String token = "";  //更新用户token;
        if(user!=null)
        {
            token = DigestUtils.md5DigestAsHex((userId+System.currentTimeMillis()).getBytes());
            user.setToken(token);
            userRepository.save(user);
        }else
        {
            throw new Exception("账号/密码错误");
        }
        return user;
    }

    /**
     * 用户信息的显示
     * @param token
     * @return
     * @throws Exception
     */
    public User infoDetail(String token) throws Exception {
        User user = userRepository.findByToken(token);
        if(user!=null)
        {
            return user;
        }
        else{
            throw new Exception("不存在该用户");
        }
    }

    public User perfectInfo(String token, String workUnit, String jobNumber, Integer age, Long phoneNumber, String position, String department) throws Exception {
        User user = userRepository.findByToken(token);
        if (user!=null)
        {
            user.setWorkUnit(workUnit);
            user.setJobNumber(jobNumber);
            user.setAge(age);
            user.setPhoneNumber(phoneNumber);
            user.setPosition(position);
            user.setDepartment(department);
            userRepository.save(user);
            return user;
        }else{
            throw new Exception("用户名不存在");
        }
    }


    /**
     * 修改密码
     * @param token
     * @param newPassword
     * @return
     * @throws Exception
     */
    public boolean changePassword(String token, String newPassword) throws Exception {
        User user = userRepository.findByToken(token);
        if(user!=null)
        {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }else {
            throw new Exception("用户不存在");
        }
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
     * @throws Exception
     */
    public User changeInfo(String token, String newWorkUnit, String newJobNumber, Integer newAge, Long newPhoneNumber, String newPosition, String newDepartment, String newEmail) throws Exception {
        User user = userRepository.findByToken(token);
        if(user!=null)
        {
            user.setWorkUnit(newWorkUnit);
            user.setJobNumber(newJobNumber);
            user.setAge(newAge);
            user.setPhoneNumber(newPhoneNumber);
            user.setPosition(newPosition);
            user.setDepartment(newDepartment);
            user.setEmail(newEmail);
            userRepository.save(user);
            return user;
        }else{
            throw new Exception("用户名不存在");
        }
    }

    public List<User> searchUser() {
        List<User> users = new ArrayList<>();
        return users;
    }
}
