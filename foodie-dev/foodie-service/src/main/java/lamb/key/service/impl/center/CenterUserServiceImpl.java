package lamb.key.service.impl.center;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lamb.key.mapper.UsersMapper;
import lamb.key.pojo.Users;
import lamb.key.pojo.center.bo.CenterUserBO;
import lamb.key.service.center.CenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author JoinYang
 * @date 2022/7/29 14:58
 * @Version 1.0
 */
@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String userId) {
        Users users = usersMapper.selectById(userId);
        users.setPassword(null);
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUsersInfo(String userId, CenterUserBO centerUserBO) {
        Users users = new Users();
        BeanUtils.copyProperties(centerUserBO,users);
        users.setId(userId);
        users.setUpdatedTime(new Date());
        int i = usersMapper.updateById(users);
        return this.queryUserInfo(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserFace(String userId, String path) {
        usersMapper.updateUserFace(path,userId);
        return this.queryUserInfo(userId);
    }
}
