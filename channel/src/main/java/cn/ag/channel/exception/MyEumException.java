package cn.ag.channel.exception;

/***
 * 自定义异常枚举类
 */
public enum MyEumException {

    TEST_1(789,"TEST1 自定义异常"),
    TEST_2(788,"TEST2 自定义异常"),
    ;

    private int code;
    private String message;

    MyEumException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
