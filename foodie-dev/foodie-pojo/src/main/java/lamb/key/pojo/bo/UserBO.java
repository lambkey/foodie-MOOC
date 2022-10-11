package lamb.key.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/14 23:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户对象BO" ,description = "从客户端，由用户传入数据封在此entity中") // swagger2
public class UserBO {

    @ApiModelProperty(value = "用户名",name = "username",required = true)
    private String username;

    @ApiModelProperty(value = "用户密码",name = "password",required = true)
    private String password;

    @ApiModelProperty(value = "用户再次输入密码",name = "confirmPassword",required = false)
    private String confirmPassword;
}
