package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author zp
 * @date 2019/3/2
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String TOKEN_PREFIX = "token_";

    public interface RedisCacheExtime {
        int REDIS_SESSION_EXTIME = 30 * 60; // 30min
    }

    // 用接口作为分组
    public interface ProductOrderBy {
        // 按价格排序
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_asc", "price_desc");
    }

    public interface Cart {
        int CHECKED = 1; // 购物车中选中状态
        int UN_CHECKED = 0; // 购物车未中选中状态
        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public interface Role {
        int ROLE_CUSTOMER = 0; // 普通用户
        int ROLE_ADMIN = 1; // 管理员
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线");

        private int code;
        private String value;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

}
