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
 * 商品评价表 
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ItemsComments对象", description="商品评价表 ")
public class ItemsComments implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id主键")
      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "用户id 用户名须脱敏")
    private String userId;

    @ApiModelProperty(value = "商品id")
    private String itemId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "商品规格id 可为空")
    private String itemSpecId;

    @ApiModelProperty(value = "规格名称 可为空")
    private String sepcName;

    @ApiModelProperty(value = "评价等级 1：好评 2：中评 3：差评")
    private Integer commentLevel;

    @ApiModelProperty(value = "评价内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;


}
