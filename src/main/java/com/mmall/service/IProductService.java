package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * @author zp
 * @date 2019/3/5
 */
public interface IProductService {

    // 更新或保存商品信息
    ServerResponse saveOrUpdateProduct(Product product);

    // 更新商品状态（上下架、删除）
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    // 后台获取商品详情
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    // 获取商品列表并分页
    ServerResponse getProductList(int pageNum, int pageSize);

    // 通过id和名称来查询商品
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    // 前台获取商品详情
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    // 前台获取商品列表
    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);

}
