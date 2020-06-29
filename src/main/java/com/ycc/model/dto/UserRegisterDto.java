package com.ycc.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author ccc
 * @date 2020/06/29
 */
@ApiModel(value = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String passWord;

    @NotEmpty(message = "年龄不能为空")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @NotEmpty(message = "手机不能为空")
    @ApiModelProperty(value = "手机")
    private String phone;

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickName;

}
