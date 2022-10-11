package lamb.key;

import lamb.key.mapper.UsersMapper;
import lamb.key.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/7/14 15:50
 * @Version 1.0
 */
@SpringBootTest(classes = Application.class)
public class Test {
    @Autowired
    private UsersMapper usersMapper;

    @org.junit.jupiter.api.Test
    public void test(){
        Map<String ,Object> map = new HashMap<>();
        map.put("username","imooc");
        List<Users> users = usersMapper.selectByMap(map);
        System.out.println(users);
    }
}
