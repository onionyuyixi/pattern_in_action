package com.example.pattern_in_action.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "有得卖回调vo")
public class EnoughToSaleCallBackVO implements Serializable {
    @ApiModelProperty(value = "工单号")
    String jobCode;
    @ApiModelProperty(value = "旧手机信息")
    List<OldPhoneExchangeInfo> oldPhoneExchangeInfos;
    @ApiModelProperty(value = "加密标记")
    String sign;
    @ApiModelProperty(value = "时间戳")
    Long timestamp;
    @ApiModelProperty(value = "回收类型 1表示邮寄到购买用户 2 表示一站式回收")
    Integer type;


    /******************一下字段在type=2的时候 必须要有****************************/
    @ApiModelProperty(value = "有得卖实体店（或者员工）地址")
    String storeAddress;
    @ApiModelProperty(value = "区县id")
    Integer storeAreaID;
    @ApiModelProperty(value = "城市id")
    Integer storeCityID;
    @ApiModelProperty(value = "省份id")
    Integer storeProvinceID;
    @ApiModelProperty(value = "区县名称")
    String storeArea;
    @ApiModelProperty(value = "城市名称")
    String storeCity;
    @ApiModelProperty(value = "省份名称")
    String storeProvince;
    @ApiModelProperty(value = "有得卖实体店（或者员工）联系电话")
    String storeMobile;
    @ApiModelProperty(value = "有得卖实体店（员工）姓名")
    String storeUserName;













}
