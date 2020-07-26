package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IProductService;
import com.mmall.vo.ProductDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zp
 * @date 2019/3/11
 */
@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    /**
     * 前台获取商品详情
     *
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    /**
     * 前台获取商品列表
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize,orderBy);
    }
    // http://www.imoocc.com:8080/product/手机/100012/1/10/price_asc
    // 每一个参数必须要写，不能为空，资源定位要唯一
    @RequestMapping(value = "/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(
            @PathVariable(value = "keyword") String keyword,
            @PathVariable(value = "categoryId") Integer categoryId,
            @PathVariable(value = "pageNum") Integer pageNum,
            @PathVariable(value = "pageSize") Integer pageSize,
            @PathVariable(value = "orderBy") String orderBy) {
        // 以下判断没用
//        if (pageNum == null) {
//            pageNum = 1;
//        }
//        if (pageSize == null) {
//            pageSize = 10;
//        }
//        if (StringUtils.isBlank(orderBy)) {
//            orderBy = "price_asc";
//        }
        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize,orderBy);
    }
}
