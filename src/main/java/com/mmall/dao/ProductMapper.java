package com.mmall.dao;

import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    // 查询商品列表
    List<Product> selectList();

    // 通过id和名称来查询商品
    List<Product> selectByNameAndProductId(@Param("productName")String productName, @Param("productId")Integer productId);

    // 通过分类id和名称来查询商品
    List<Product> selectByNameAndCategoryIds(@Param("productName")String productName, @Param("categoryIdList")List<Integer> categoryIdList);
}