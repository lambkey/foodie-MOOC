package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author JoinYang
 * @date 2022/7/22 2:03
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsCommentsVO {
    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
