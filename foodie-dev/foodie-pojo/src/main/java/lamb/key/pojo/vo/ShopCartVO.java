package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/23 23:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartVO {
        private String itemId;
		private String itemImgUrl;
		private String itemName;
        private String specId;
        private String specName;
        private String priceDiscount;
        private String priceNormal;
}
