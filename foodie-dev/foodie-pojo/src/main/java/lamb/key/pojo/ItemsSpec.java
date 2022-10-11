package lamb.key.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ItemsSpec对象", description="商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计")
public class ItemsSpec implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "商品规格id")
      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "商品外键id")
    private String itemId;

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "折扣力度")
    private BigDecimal discounts;

    @ApiModelProperty(value = "优惠价")
    private Integer priceDiscount;

    @ApiModelProperty(value = "原价")
    private Integer priceNormal;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;


}
