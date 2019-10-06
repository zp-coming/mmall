package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;

/**
 * @author zp
 * @date 2019/6/18
 */
public interface IShippingService {
    // 添加收货地址
    ServerResponse add(Integer userId, Shipping shipping);

    // 删除收货地址
    ServerResponse<String> del(Integer userId, Integer shippingId);

    // 更新收货地址
    ServerResponse update(Integer userId, Shipping shipping);

    // 查询收货地址
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}