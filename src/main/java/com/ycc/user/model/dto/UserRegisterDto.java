package com.ycc.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ccc
 * @date 2020/06/29
 */
@ApiModel(value = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @ApiModelProperty(value = "用户名",allowableValues = "chanchal")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "用户名",allowableValues = "123456")
    @NotEmpty(message = "用户名不能为空")
    private String passWord;

    @NotNull(message = "年龄不能为空")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @NotEmpty(message = "手机不能为空")
    @ApiModelProperty(value = "手机",allowableValues = "13202893205")
    private String phone;

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称",allowableValues = "小春子")
    private String nickName;

}
