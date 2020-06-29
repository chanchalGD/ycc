package com.ycc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String passWord;
}
