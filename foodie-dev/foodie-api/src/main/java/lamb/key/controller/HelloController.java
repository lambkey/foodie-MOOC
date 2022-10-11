package lamb.key.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author JoinYang
 * @date 2022/7/13 2:30
 * @Version 1.0
 */
//@Controller
@ApiIgnore  // 不允许swagger2暴露本类api
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public Object hello(){
        return "hello foodie";
    }
}
