package cn.ag.channel.exception;

public class MyException extends RuntimeException{

    private Integer code;

    public MyException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public MyException(MyEumException e){
        super(e.getMessage());
        this.code = e.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
