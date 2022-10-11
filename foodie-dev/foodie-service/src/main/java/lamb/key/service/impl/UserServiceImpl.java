package lamb.key.service.impl;



import lamb.key.mapper.UsersMapper;
import lamb.key.pojo.Users;
import lamb.key.pojo.bo.UserBO;
import lamb.key.service.UserService;
import lamb.key.utils.DateUtil;
import lamb.key.utils.enums.Sex;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/7/14 14:57
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String user_face="https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/6/15/28227d19-8870-4711-a08f-a861940ae087.png";

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED )
    @Override
    public Users queryUserForLogin(String username, String password) {
        Users users = usersMapper.queryUserForLogin(username);
        String password1 = users.getPassword();
        boolean matches = passwordEncoder.matches(password, password1);
        if (!matches){
            return null;
        }
        return users;
    }
    
    
    @Transactional(propagation = Propagation.REQUIRED )
    @Override
    public boolean queryUsernameIsExist(String username) {
        Map<String ,Object> map = new HashMap<>();
        map.put("username",username);
        List<Users> users = usersMapper.selectByMap(map);
        return users.isEmpty();
    }

    @Transactional(propagation = Propagation.REQUIRED )
    @Override
    public Users createUser(UserBO userBO) {
        Users users = new Users();
        users.setId(sid.nextShort());
        String encode = passwordEncoder.encode(userBO.getPassword());
        users.setUsername(userBO.getUsername());
        users.setPassword(encode);

        // 默认用户昵称同用户名
        users.setNickname(userBO.getUsername());
        // 设置默认头像
        users.setFace(user_face);
        // 设置用户的默认生日
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 设置用户性别为保密
        users.setSex(Sex.secret.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);
        return users;
    }
    

}
