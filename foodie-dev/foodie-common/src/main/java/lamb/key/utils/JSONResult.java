package lamb.key.utils;

/**
 * @author JoinYang
 * @date 2022/7/14 23:02
 * @Version 1.0
 */
public class JSONResult <E>{

    public static final Integer SUCCESS=200;
    public static final Integer FAILURE=500;

    //用来封装当前请求的结果是成功还是失败
    private Integer status;

    //请求处理失败返回的错误信息
    private String message;

    //要返回的数据
    private Object data;

    //请求处理成功且不需要返回信息和数据时
    public static JSONResult successWithoutData(){
        return new JSONResult(200,null,null);
    }

    //请求处理成功需要返回信息和数据时
    public static JSONResult successNeedData(Object data){
        return new JSONResult(200,null,data);
    }

    public static JSONResult successNeedMessage(String message){
        return new JSONResult(200,message,null);
    }
    //请求处理失败时返回错误信息
    public static JSONResult FailureNeedMessage(String message){
        return new JSONResult(500,message,null);
    }
    public static JSONResult FailureNeedData(Object data){
        return new JSONResult(500,null,data);
    }

    public static JSONResult errorMsg(String msg) {
        return new JSONResult(500, msg, null);
    }

    public static JSONResult errorMap(Object data) {
        return new JSONResult(501, "error", data);
    }

    public JSONResult() {
    }

    public JSONResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}