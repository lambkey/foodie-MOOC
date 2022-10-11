package lamb.key.pojo.center.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/31 2:34
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManagerVO {
    /*
    *       od.id as orderId,
            od.created_time as createdTime,
            od.pay_method as payMethod,
            od.real_pay_amount as realPayAmount,
            od.post_amount as post_amount,
            oi.id,
            oi.item_img as itemImg,
            oi.item_name as itemName,
            oi.item_spec_id as itemSpecId,
            oi.item_spec_name as itemSpecName,
            oi.price as price,
            oi.buy_counts as buyCounts
    * */
    private String orderId;
    private Date  createdTime;
    private int payMethod;
    private int realPayAmount;
    private int postAmount;
    private int orderStatus;
    private int isComment;
    private List<SubOrderManagerVO> subOrderItemList;
}
