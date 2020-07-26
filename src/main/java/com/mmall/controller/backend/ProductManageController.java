package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zp
 * @date 2019/3/5
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    /**
     * 更新或保存商品信息
     *
     * @param request
     * @param product
     * @return
     */
    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpServletRequest request, Product product) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iProductService.saveOrUpdateProduct(product);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }

        // 全部通过拦截器验证是否登录以及权限
        return iProductService.saveOrUpdateProduct(product);
    }

    /**
     * 更新商品状态（上下架、删除）
     *
     * @param request
     * @param productId
     * @param status
     * @return
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpServletRequest request, Integer productId, Integer status) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iProductService.setSaleStatus(productId, status);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }

        // 全部通过拦截器验证是否登录以及权限
        return iProductService.setSaleStatus(productId, status);

    }

    /**
     * 获取商品详情
     *
     * @param request
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpServletRequest request, Integer productId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iProductService.manageProductDetail(productId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }

        // 全部通过拦截器验证是否登录以及权限
        return iProductService.manageProductDetail(productId);
    }

    /**
     * 获取商品列表并分页
     *
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iProductService.getProductList(pageNum, pageSize);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }

        // 全部通过拦截器验证是否登录以及权限
        return iProductService.getProductList(pageNum, pageSize);
    }

    /**
     * 通过id和名称来查询商品
     *
     * @param request
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse searchProduct(HttpServletRequest request, String productName, Integer productId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iProductService.searchProduct(productName, productId, pageNum, pageSize);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }

        // 全部通过拦截器验证是否登录以及权限
        return iProductService.searchProduct(productName, productId, pageNum, pageSize);
    }

    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFilename = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFilename;
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFilename);
            fileMap.put("url", url);
            return ServerResponse.createBySuccess(fileMap);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        // 富文本中对于返回值有自己的要求，我们使用simditor,所以按照simditor的要求返回
        /*
        {
            "success": true/false,
            "msg": "error message", # optional
            "file_path": "[real file path]"
        }
        */
        Map resultMap = Maps.newHashMap();
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员");
            return resultMap;
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user == null) {
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员");
            return resultMap;
        }

        if (iUserService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFilename = iFileService.upload(file, path);
            if (StringUtils.isBlank(targetFilename)) {
                resultMap.put("success", false);
                resultMap.put("msg", "上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFilename;
            resultMap.put("success", true);
            resultMap.put("msg", "上传成功");
            resultMap.put("file_path", url);
            response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
            return resultMap;
        } else {
            resultMap.put("success", false);
            resultMap.put("msg", "无权限操作");
            return resultMap;
        }
    }
}
