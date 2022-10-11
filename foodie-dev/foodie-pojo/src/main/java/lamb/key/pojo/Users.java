package lamb.key.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表 
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Users对象", description="用户表 ")
public class Users implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id 用户id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "用户名 用户名")
    private String username;

    @ApiModelProperty(value = "密码 密码")
    private String password;

    @ApiModelProperty(value = "昵称 昵称")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "头像")
    private String face;

    @ApiModelProperty(value = "手机号 手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱地址 邮箱地址")
    private String email;

    @ApiModelProperty(value = "性别 性别 1:男  0:女  2:保密")
    private Integer sex;

    @ApiModelProperty(value = "生日 生日")
    private Date birthday;

    @ApiModelProperty(value = "创建时间 创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间 更新时间")
    private Date updatedTime;


}
