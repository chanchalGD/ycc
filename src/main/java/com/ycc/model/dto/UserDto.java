package com.ycc.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;

    @NotEmpty(message = "地址不能为空")
    @ApiModelProperty(value = "地址")
    private String address;

    @NotEmpty(message = "年龄不能为空")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @NotEmpty(message = "手机不能为空")
    @ApiModelProperty(value = "手机")
    private String phone;

    @NotEmpty(message = "性别不能为空")
    @ApiModelProperty(value = "性别")
    private Integer sex;


    @NotEmpty(message = "身份证号不能为空")
    @ApiModelProperty(value = "身份证号")
    private Integer idNum;

}
