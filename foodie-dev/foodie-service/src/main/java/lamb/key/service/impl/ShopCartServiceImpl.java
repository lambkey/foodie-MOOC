package lamb.key.service.impl;

import lamb.key.mapper.ItemsMapper;
import lamb.key.pojo.vo.ShopCartVO;
import lamb.key.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/25 14:02
 * @Version 1.0
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopCartVO> queryItemsBySpecIds(String specIds) {
        String[] sId = specIds.split(",");
        List<String> list = new ArrayList<>();
        Collections.addAll(list,sId);
        List<ShopCartVO> shopCartVOS = itemsMapper.queryItemsBySpecIds(list);
        return shopCartVOS;
    }
}
