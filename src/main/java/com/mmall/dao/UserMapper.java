package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 检查用户名是否已存在
    int checkUsername(String username);

    // 检查email是否已存在
    int checkEmail(String email);

    // 登录成功，返回用户信息
    User selectLogin(@Param("username")String username, @Param("password")String password);

    // 找回密码问题
    String selectQuestionByUsername(String username);

    // 找回问题答案
    int checkAnswer(@Param("username")String username, @Param("question")String question, @Param("answer")String answer);

    // 忘记密码时，根据用户名更新密码
    int updatePasswordByUsername(@Param("username")String username, @Param("passwordNew")String passwordNew);

    // 登录状态时，更新密码
    int checkPassword(@Param("password")String password, @Param("userId")Integer userId);

    // 根据用户id校验email
    int checkEmailByUserId(@Param("email")String email, @Param("userId")Integer userId);
}