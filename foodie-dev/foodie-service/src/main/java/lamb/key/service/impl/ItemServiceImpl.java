package lamb.key.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lamb.key.mapper.*;
import lamb.key.pojo.Items;
import lamb.key.pojo.ItemsImg;
import lamb.key.pojo.ItemsParam;
import lamb.key.pojo.ItemsSpec;
import lamb.key.pojo.vo.ItemsCommentsCountVO;
import lamb.key.pojo.vo.ItemsCommentsVO;
import lamb.key.pojo.vo.SearchItemVO;
import lamb.key.service.ItemService;
import lamb.key.utils.DesensitizationUtil;
import lamb.key.utils.PagedGridResult;
import lamb.key.utils.enums.CommentsLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/7/20 16:06
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Override
    public Items queryItemDetailByItemId(String itemId) {
        Items items = itemsMapper.selectById(itemId);
        return items;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecListByItemID(String itemId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("item_id",itemId);
        List list = itemsSpecMapper.selectList(queryWrapper);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParamByItemId(String itemId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("item_id",itemId);
        ItemsParam itemsParam = itemsParamMapper.selectOne(queryWrapper);
        return itemsParam;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImageByItemId(String itemId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("item_id",itemId);
        List list = itemsImgMapper.selectList(queryWrapper);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsCommentsCountVO queryItemCommentCount(String itemId) {
        Integer GOOD = queryCommentCountByCommentLevel(itemId, CommentsLevel.GOOD.type);
        Integer NORMAL = queryCommentCountByCommentLevel(itemId, CommentsLevel.NORMAL.type);
        Integer BAD = queryCommentCountByCommentLevel(itemId, CommentsLevel.BAD.type);
        Integer total = GOOD+NORMAL+BAD;

        ItemsCommentsCountVO itemsCommentsCountVO = new ItemsCommentsCountVO();
        itemsCommentsCountVO.setGoodCounts(GOOD);
        itemsCommentsCountVO.setNormalCounts(NORMAL);
        itemsCommentsCountVO.setBadCounts(BAD);
        itemsCommentsCountVO.setTotalCounts(total);
        return itemsCommentsCountVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer queryCommentCountByCommentLevel(String itemId,Integer level){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("item_id",itemId);
        queryWrapper.eq("comment_level",level);
        return itemsCommentsMapper.selectCount(queryWrapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPageComments(String itemId, Integer level,Integer page,Integer pageSize) {

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("itemId",itemId);
        paramsMap.put("level",level);

        /**
         * page: 第几页
         * pageSize: 每页显示条数
         * */

        PageHelper.startPage(page, pageSize);

        List<ItemsCommentsVO> itemsCommentsVOs = itemsCommentsMapper.queryItemComments(paramsMap);

        /*
        * 评论匿名设置
        * */
        for (ItemsCommentsVO itemsCommentsVO : itemsCommentsVOs) {
            itemsCommentsVO.setNickname(DesensitizationUtil.commonDisplay(itemsCommentsVO.getNickname()));
        }
        PagedGridResult pagedGridResult = pageHelper(page,itemsCommentsVOs);

        return pagedGridResult;
    }

    private PagedGridResult pageHelper(Integer page,List<?> list){

        PageInfo<?> pageInfo = new PageInfo<>(list);
        PagedGridResult pagedGridResult = new PagedGridResult();

        pagedGridResult.setPage(page);
        // 总记录数
        pagedGridResult.setRecords(pageInfo.getTotal());
        // 总页数
        pagedGridResult.setTotal(pageInfo.getPages());
        pagedGridResult.setRows(list);

        return pagedGridResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItemByKeywordsPage(String keyword, String sort, Integer page, Integer pageSize) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("keywords",keyword);
        paramsMap.put("sort",sort);
        PageHelper.startPage(page,pageSize);
        List<SearchItemVO> searchItemVOS = itemsMapper.searchItemByKeywords(paramsMap);
        PagedGridResult pagedGridResult = pageHelper(page, searchItemVOS);
        return pagedGridResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItemByThirdCatPage(Integer catId, String sort, Integer page, Integer pageSize) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("catId",catId);
        paramsMap.put("sort",sort);
        PageHelper.startPage(page,pageSize);
        List<SearchItemVO> searchItemVOS = itemsMapper.searchItemByThirdCat(paramsMap);
        PagedGridResult pagedGridResult = pageHelper(page, searchItemVOS);
        return pagedGridResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsSpec queryItemSpecInfo(String specId) {
        ItemsSpec itemsSpec = itemsSpecMapper.selectById(specId);
        return itemsSpec;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemByItemId(String itemId) {
        Items items = itemsMapper.selectById(itemId);
        return items;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsImg queryItemImgIsMainByItemId(String itemId) {
        Map<String, Object> map = new HashMap();
        map.put("item_id",itemId);
        map.put("is_main",1);
        List<ItemsImg> itemsImgs = itemsImgMapper.selectByMap(map);
        return itemsImgs.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void decreaseItemSpecStock(String specId, int buyCounts) {
        // synchronized 不推荐使用,集群下无用,性能低下
        // 锁数据：不推荐，导致数据库性能低下
        // 分布式锁 zookeeper redis
        int result = itemsSpecMapper.decreaseItemSpecStock(specId, buyCounts);
        if (result!=1){
            throw new RuntimeException("订单创建失败，库存不足");
        }
    }

}
