package com.ycc.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel(value = "接口返回对象",description = "接口返回对象")
@NoArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回对象的结果")
    private boolean result;


    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T data;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();


    private Integer code;


    private String message;


    /**
     * 成功返回无参
     **/
    public Response<T> succcess(){
        Response<T>response = new Response<>();
        response.setMessage(ResponseStatus.SUCCESS.getMessage());
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setResult(true);
        return response;
    }

    /**
     * @param data
     **/
    public Response<T> succcess(T data){
        Response<T>response = new Response<>();
        response.setMessage(ResponseStatus.SUCCESS.getMessage());
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setResult(true);
        response.setData(data);
        return response;
    }

    /**
     *
     **/
    public Response<T> failed(){
        Response<T>response = new Response<>();
        response.setMessage(ResponseStatus.FAILED.getMessage());
        response.setCode(ResponseStatus.FAILED.getCode());
        response.setResult(false);
        return response;
    }

    /**
     * @param message
     **/
    public Response<T> failed(String message){
        Response<T>response = new Response<>();
        response.setCode(ResponseStatus.FAILED.getCode());
        response.setResult(false);
        response.setMessage(message);
        return response;
    }

    /**
     * @param message
     * @param data
     **/
    public Response<T> failed(T data,String message){
        Response<T>response = new Response<>();
        response.setMessage(message);
        response.setCode(ResponseStatus.FAILED.getCode());
        response.setResult(false);
        response.setData(data);
        return response;
    }



}
