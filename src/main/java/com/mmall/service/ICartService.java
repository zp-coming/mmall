package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * @author zp
 * @date 2019/3/23
 */
public interface ICartService{

    // 查询商品
    ServerResponse list(Integer userId);

    // 添加商品
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    // 更新商品
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    // 删除商品
    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    // 选择商品
    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    // 获取购物车的产品数量
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
