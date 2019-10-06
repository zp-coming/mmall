package com.mmall.pojo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PayInfo {
    private Integer id;

    private Integer userId;

    private Long orderNo;

    private Integer payPlatform;

    private String platformNumber;

    private String platformStatus;

    private Date createTime;

    private Date updateTime;

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber == null ? null : platformNumber.trim();
    }


    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus == null ? null : platformStatus.trim();
    }
}