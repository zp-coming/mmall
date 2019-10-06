package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * @author zp
 * @date 2019/3/3
 */
public interface ICategoryService {
    // 添加分类
    ServerResponse addCategory(String categoryName, Integer parentId);

    // 更新品类名字
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    // 查询子节点的category信息,并且不递归,保持平级
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    // 查询当前节点的id和递归子节点的id
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
