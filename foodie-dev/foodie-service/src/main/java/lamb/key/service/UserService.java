package lamb.key.service;

import lamb.key.pojo.Users;
import lamb.key.pojo.bo.UserBO;

/**
 * @author JoinYang
 * @date 2022/7/14 14:54
 * @Version 1.0
 */
public interface UserService {
    /*
    * 判断用户名是否存在
    *
    * */
    boolean queryUsernameIsExist(String username);

    /*
     * 创建新用户
     *
     * */
    Users createUser(UserBO userBO);

    /*
    * 检索用户传入的账号密码，查询用户，校验登录
    *
    * */
    Users queryUserForLogin(String username,String password);
}
