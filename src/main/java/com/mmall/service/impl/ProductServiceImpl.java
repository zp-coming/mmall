package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.ICategoryService;
import com.mmall.service.IProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zp
 * @date 2019/3/5
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    // 平级调用，service调用service
    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 更新或保存商品信息
     * @param product
     * @return
     */
    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (StringUtils.isNotBlank(product.getSubImages())) {
                String[] subImagesArray = product.getSubImages().split(",");
                if (subImagesArray.length > 0) {
                    product.setMainImage(subImagesArray[0]);
                }
                // 更新或新增
                if (product.getId() != null) {
                    int rowCount = productMapper.updateByPrimaryKey(product);
                    if (rowCount > 0) {
                        return ServerResponse.createBySuccess("更新商品成功");
                    }
                    return ServerResponse.createByErrorMessage("更新商品失败");
                } else {
                    int rowCount = productMapper.insert(product);
                    if (rowCount > 0) {
                        return ServerResponse.createBySuccess("新增商品成功");
                    }
                    return ServerResponse.createByErrorMessage("新增商品失败");
                }
            }

        }
        return ServerResponse.createByErrorMessage("新增或更新商品参数不正确");
    }

    /**
     * 更新商品状态（上下架、删除）
     * @param productId
     * @param status
     * @return
     */
    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status ==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改商品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改商品销售状态失败");
    }

    /**
     * 获取商品详情
     * @param productId
     * @return
     */
    @Override
    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ServerResponse.createByErrorMessage("商品已下架或已删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }


    private ProductDetailVo assembleProductDetailVo(Product product) {
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStock(product.getStock());

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if (category == null) {
            productDetailVo.setParentCategoryId(0);
        } else {
            productDetailVo.setParentCategoryId(category.getParentId());
        }

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));

        return productDetailVo;
    }


    /**
     * 获取商品列表并分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getProductList(int pageNum, int pageSize) {
        // startPage
        // 填充自己的sql查询逻辑
        // pageHelper收尾
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectList();
        List<ProductListVo> productListVoList = new ArrayList<>();
        for (Product productItem : productList) {
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }


    private ProductListVo assembleProductListVo(Product product) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
        productListVo.setMainImage(product.getMainImage());
        productListVo.setPrice(product.getPrice());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setStatus(product.getStatus());
        return productListVo;
    }

    /**
     * 通过id和名称来查询商品
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotBlank(productName)) {
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }

        List<Product> productList = productMapper.selectByNameAndProductId(productName, productId);
        List<ProductListVo> productListVoList = new ArrayList<>();
        for (Product productItem : productList) {
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 前台获取商品详情
     * @param productId
     * @return
     */
    @Override
    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null || product.getStatus() != Const.ProductStatusEnum.ON_SALE.getCode()) {
            return ServerResponse.createByErrorMessage("商品已下架或已删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);

        return ServerResponse.createBySuccess(productDetailVo);
    }

    /**
     * 前台获取商品列表
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy) {
        // 参数校验
        if (StringUtils.isBlank(keyword) && categoryId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        // 当传入一个大的父类的id，需要把下面的所有子类查出来
        List<Integer> categoryIdList = new ArrayList<>();

        if (categoryId != null) {
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if (category == null && StringUtils.isBlank(keyword)) {
                // 没有该分类且没有关键字，此时返回空的结果集，不报错
                PageHelper.startPage(pageNum, pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            if (category != null) {
                categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
            }
        }

        if (StringUtils.isNotBlank(keyword)) {
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        PageHelper.startPage(pageNum, pageSize);
        // 排序处理
        if (StringUtils.isNotBlank(orderBy)) {
            if (Const.ProductOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0] + " " + orderByArray[1]);
            }
        }

        List<Product> productList = productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword, categoryIdList.size()==0?null:categoryIdList);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product product : productList) {
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
