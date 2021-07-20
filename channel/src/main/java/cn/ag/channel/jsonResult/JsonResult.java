package cn.ag.channel.jsonResult;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private static final long serialVersionUID = -4699713095477151086L;

    private Object data;
    private Integer code;
    private String message;

    public JsonResult(){}

    public JsonResult(Object data) {
        this.data = data;
        this.code = 0;
        this.message = "成功";
    }

    public JsonResult(Object data,int code,String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static JsonResult success(Object data){
        return new JsonResult(data);
    }

    public static JsonResult fail(String message){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(-1);
        jsonResult.setMessage("系统错误，请稍后再试");
        return jsonResult;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
