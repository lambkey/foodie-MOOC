package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/20 14:04
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsItemVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;
    private List<SixNewsItemVO> simpleItemList;
}
