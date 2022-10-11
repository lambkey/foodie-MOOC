package lamb.key.service.center;

import lamb.key.pojo.Users;
import lamb.key.pojo.bo.UserBO;
import lamb.key.pojo.center.bo.CenterUserBO;

/**
 * @author JoinYang
 * @date 2022/7/14 14:54
 * @Version 1.0
 */
public interface CenterUserService {
    /*
    * 根据用户id查询用户信息
    * */
    public Users queryUserInfo(String userId);

    /*
    * 根据用户id更新用户个人信息
    * */
    public Users updateUsersInfo(String userId, CenterUserBO centerUserBO);

    /*
    * 根据用户id更新用户头像
    * */
    public Users updateUserFace(String userId,String path);
}
