package lamb.key.pojo.center.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author JoinYang
 * @date 2022/7/29 17:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterUserBO {

    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 12,message = "用户昵称不能超过12")
    @ApiModelProperty(value = "昵称 昵称")
    private String nickname;

    @Length(max = 12,message = "用户真实姓名不能超过12")
    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$",message = "请输入有效的手机号码")
    @ApiModelProperty(value = "手机号 手机号")
    private String mobile;

    @Email
    @ApiModelProperty(value = "邮箱地址 邮箱地址")
    private String email;

    @Min(value = 0,message = "性别选择不正确")
    @Max(value = 2,message = "性别选择不正确")
    @ApiModelProperty(value = "性别 性别 1:男  0:女  2:保密")
    private Integer sex;

    @ApiModelProperty(value = "生日 生日")
    private Date birthday;


    @ApiModelProperty(value = "更新时间 更新时间")
    private Date updatedTime;
}
