package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zp
 * @date 2019/6/18
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    /**
     * 添加收货地址
     *
     * @param userId
     * @param shipping
     * @return
     */
    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map<String, Integer> resultMap = Maps.newHashMap();
            resultMap.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功", resultMap);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    /**
     * 删除收货地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    @Override
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        // 横向越权： 防止传入的shippingId为别人的id，所以这里删除要加上已登录用户的id
        int resultRow = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (resultRow > 0) {
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createBySuccess("删除地址失败");
    }

    /**
     * 更新收货地址
     *
     * @param userId
     * @param shipping
     * @return
     */
    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    /**
     * 查询收货地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    @Override
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }

        return ServerResponse.createBySuccess("查询地址成功", shipping);
    }

    /**
     * 分页查询收货地址
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
