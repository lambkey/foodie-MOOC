package lamb.key.pojo.vo;

import lamb.key.pojo.Items;
import lamb.key.pojo.ItemsImg;
import lamb.key.pojo.ItemsParam;
import lamb.key.pojo.ItemsSpec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/20 16:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
