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
 * 订单表;
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Orders对象", description="订单表;")
public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单主键;同时也是订单编号")
      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "收货人快照")
    private String receiverName;

    @ApiModelProperty(value = "收货人手机号快照")
    private String receiverMobile;

    @ApiModelProperty(value = "收货地址快照")
    private String receiverAddress;

    @ApiModelProperty(value = "订单总价格")
    private Integer totalAmount;

    @ApiModelProperty(value = "实际支付总价格")
    private Integer realPayAmount;

    @ApiModelProperty(value = "邮费;默认可以为零，代表包邮")
    private Integer postAmount;

    @ApiModelProperty(value = "支付方式")
    private Integer payMethod;

    @ApiModelProperty(value = "买家留言")
    private String leftMsg;

    @ApiModelProperty(value = "扩展字段")
    private String extand;

    @ApiModelProperty(value = "买家是否评价;1：已评价，0：未评价")
    private Integer isComment;

    @ApiModelProperty(value = "逻辑删除状态;1: 删除 0:未删除")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间（成交时间）")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;


}
