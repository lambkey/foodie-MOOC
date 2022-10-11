package lamb.key.utils;


/**
 * @author JoinYang
 * @date 2022/6/9 16:39
 * @Version 1.0
 */
public class UploadMsg {
    private int status;
    private String message;
    private String path;

    public UploadMsg() {
    }

    public UploadMsg(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "UploadMsg{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
