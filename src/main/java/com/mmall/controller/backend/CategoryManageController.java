package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zp
 * @date 2019/3/3
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 管理员添加分类
     * @param request
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest request, String categoryName, @RequestParam(value = "parentId",defaultValue = "0")int parentId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
//        }
//        // 校验是否是管理员
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.addCategory(categoryName, parentId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
//        }
        // 全部通过拦截器验证是否登录以及权限
        return iCategoryService.addCategory(categoryName, parentId);
    }

    /**
     * 更新品类名字
     * @param request
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpServletRequest request, Integer categoryId, String categoryName) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
//        }
//        // 校验是否是管理员
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            // 更新CategoryName
//            return iCategoryService.updateCategoryName(categoryId, categoryName);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
//        }
        // 全部通过拦截器验证是否登录以及权限
        return iCategoryService.updateCategoryName(categoryId, categoryName);
    }

    /**
     * 查询子节点的category信息,并且不递归,保持平级
     * @param request
     * @param categoryId
     * @return
     */
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpServletRequest request, @RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
//        }
//        // 校验是否是管理员
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            // 查询子节点的category信息,并且不递归,保持平级
//            return iCategoryService.getChildrenParallelCategory(categoryId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
//        }

        // 全部通过拦截器验证是否登录以及权限
        // 查询子节点的category信息,并且不递归,保持平级
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    /**
     * 递归查询当前节点id和子节点的id
     * @param request
     * @param categoryId
     * @return
     */
    @RequestMapping("get_deep_Category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpServletRequest request, @RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
//        }
//        // 校验是否是管理员
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            // 查询当前节点的id和递归子节点的id
//            return iCategoryService.selectCategoryAndChildrenById(categoryId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作，需管理员权限");
//        }

        // 全部通过拦截器验证是否登录以及权限
        // 查询当前节点的id和递归子节点的id
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }

}
