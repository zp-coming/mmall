package com.mmall.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer id;

    private Integer userId;

    private Long orderNo;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createTime;

    private Date updateTime;

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage == null ? null : productImage.trim();
    }
}