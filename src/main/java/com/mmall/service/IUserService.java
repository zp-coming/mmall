package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * @author zp
 * @date 2019/3/2
 */
public interface IUserService {
    // 用户登录
    ServerResponse<User> login(String username, String password);

    // 用户注册
    ServerResponse<String> register(User user);

    // 用户名和email校验
    ServerResponse<String> checkValid(String str, String type);

    // 找回密码问题
    ServerResponse<String> selectQuestion(String username);

    // 找回密码答案
    ServerResponse<String> checkAnswer(String username, String question, String answer);

    // 忘记密码：重置密码
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    // 登录状态下更新密码
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    // 更新个人信息
    ServerResponse<User> updateUserInfo(User user);

    // 获取个人信息
    ServerResponse<User> getInformation(Integer userId);

    // 校验是否是管理员
//    ServerResponse checkAdminRole(User user);
}
