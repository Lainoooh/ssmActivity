package com.ssm.activity.domain;


import java.io.Serializable;

/** 用于Web的请求返回;
 * 状态码说明，与Http状态码保持一置：{@link https://www.runoob.com/http/http-status-codes.html}
 * <br/>
 * 1**	信息，服务器收到请求，需要请求者继续执行操作<br/>
 * 2**	成功，操作被成功接收并处理<br/>
 * 3**	重定向，需要进一步的操作以完成请求<br/>
 * 4**	客户端错误，请求包含语法错误或无法完成请求<br/>
 * 5**	服务器错误，服务器在处理请求的过程中发生了错误<br/>
 *
 */
public class WebResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int RST_SUCCESS=200;
    public static final int RST_FAIL=201;
    //203登录失败
    public static final int LOGIN_FAIL=203;

    public static final int RST_PARAMER_FAIL=400;
    /**要求用户的身份认证*/
    public static final int UNAUTGORIZED=401;
    /**服务器理解请求客户端的请求，但是拒绝执行此请求*/
    public static final int FORBIDDEN=403;

    public static final int RST_INTERFACE_ERROR=400;
    /**服务器内部错误，无法完成请求*/
    public static final int RST_SERVER_ERROR=500;

    /**License失效或者验证失败，前端提示后，跳转至登录页面进行license校验*/
    public static final int RST_NOT_EXTENDED=510;

    public static final String PRE_ERROR = "";

//    @ApiModelProperty(value = "返回操作码：200:成功，201:失败，400:请求参数错误，404:接口不支持，500:服务器错误")
    private int code;
//    @ApiModelProperty(value = "返回数据结果")
    private T result;
//    @ApiModelProperty(value = "返回错误信息（操作错误或异常时）")
    private String error;

    public void setResult(T result) {
        this.setCode(RST_SUCCESS);
        this.result = result;
    }

    public void setResultError(T result) {
        this.result = result;
    }

    public void setError(String error) {
        this.setCode(RST_FAIL);
        this.error = error;
    }

    public boolean isSuccess() {
        return code==RST_SUCCESS;
    }
    @SuppressWarnings("unchecked")
    public static <T> WebResult<T> ok(T data) {
        WebResult<T> resp = new WebResult<>();
        resp.setResult(data);
        resp.setCode(RST_SUCCESS);
        return resp;
    }

    public static <T> WebResult<T> ok() {
        return ok(null);
    }


//    public static <T> WebResult<PageInfo<T>> pageOk(Object t){
//        return ok(PageInfo.of(t));
//    }


    public static <T> WebResult<T> fail(String error,int code,T t) {
        WebResult<T> resp = new WebResult<>();

        if (!error.startsWith(PRE_ERROR)) {
            error = PRE_ERROR + error;
        }
        resp.setError(error);
        resp.setCode(code);
        resp.setResultError(t);
        return resp;
    }

    public static <T> WebResult<T> fail(String error) {
        return fail(error,RST_FAIL,null);
    }

    public static <T> WebResult<T> fail(String error,T t) {
        return fail(error,RST_FAIL,t);
    }

    public static <T> WebResult<T> fail(String error,int code) {
        return fail(error,code,null);
    }

    public T getResult() {
        return this.result;
    }

    public String getError() {
        return this.error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
