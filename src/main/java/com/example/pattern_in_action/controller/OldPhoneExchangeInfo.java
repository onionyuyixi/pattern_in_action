package com.example.pattern_in_action.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "旧手机的信息")
public class OldPhoneExchangeInfo implements Serializable {

    @ApiModelProperty(value = "补贴金额")
    BigDecimal subsidyAmount;
    @ApiModelProperty(value = "旧机型号")
    String oldPhoneStyle; //旧机型号
    @ApiModelProperty(value = "旧机估值")
    String oldPhoneValue;//旧机估值
    @ApiModelProperty(value = "旧机链接")
    String imageUrl;
    @ApiModelProperty(value = "授权时间")
    String creditDateStr;
    @ApiModelProperty(value = "授权额度")
    String creditQuota;
    @ApiModelProperty(value = "旧机分类")
    String category;

}
