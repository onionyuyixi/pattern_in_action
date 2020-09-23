package com.example.pattern_in_action.monad;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("person")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @ApiModelProperty(value = "name", example = "onion")
    private String name;
    @ApiModelProperty(value = "age", example = "27")
    private int age;
    @ApiModelProperty(value = "sex")
    private Sex sex;
    @ApiModelProperty(value = "email", example = "2703012@yuyixi.com")
    private String email;


}
