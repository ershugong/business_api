package cn.ag.channel.jsonResult;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = -4699713095477151086L;

    @ApiModelProperty(value = "具体数据")
    private T data;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回码信息")
    private String message;

    public JsonResult(){}

    public JsonResult(T data) {
        this.data = data;
        this.code = 0;
        this.message = "成功";
    }

    public JsonResult(T data,int code,String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> JsonResult<T> success(T data){
        return new JsonResult<T>(data);
    }

    public static <T> JsonResult<T> fail(String message){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(-1);
        jsonResult.setMessage("系统错误，请稍后再试");
        return jsonResult;
    }

    public static <T> JsonResult<T> failDefault(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(-1);
        jsonResult.setMessage("系统错误，请稍后再试");
        return jsonResult;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
