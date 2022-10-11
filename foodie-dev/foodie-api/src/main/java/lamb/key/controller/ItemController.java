package lamb.key.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.pojo.Items;
import lamb.key.pojo.ItemsImg;
import lamb.key.pojo.ItemsParam;
import lamb.key.pojo.ItemsSpec;
import lamb.key.pojo.vo.ItemInfoVO;
import lamb.key.pojo.vo.ItemsCommentsCountVO;
import lamb.key.pojo.vo.ShopCartVO;
import lamb.key.service.ItemService;
import lamb.key.service.ShopCartService;
import lamb.key.utils.JSONResult;
import lamb.key.utils.PagedGridResult;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/20 16:41
 * @Version 1.0
 */
@Api(value = "商品",tags = "商品详情展示")
@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ShopCartService shopCartService;

    @ApiOperation(value = "根据（可以是以，号拼接的）商品规格id查询商品",notes ="根据（可以是以，号拼接的）商品规格id查询商品" )
    @GetMapping("/refresh")
    public JSONResult queryItemsBySpecIds(@RequestParam("itemSpecIds") String itemSpecIds){
        if (StringUtils.isBlank(itemSpecIds)){
            return JSONResult.successWithoutData();
        }
        List<ShopCartVO> shopCartVOS = shopCartService.queryItemsBySpecIds(itemSpecIds);
        return JSONResult.successNeedData(shopCartVOS);

    }

    @ApiOperation(value = "三级分类id查询商品",notes = "三级分类id查询商品",httpMethod = "GET")
    @GetMapping("/catItems")
    public JSONResult searchItemByThirdCatPage(
            @RequestParam(name = "catId") Integer catId,
            @RequestParam(name = "sort") String sort,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "20") Integer pageSize
    ){
        PagedGridResult pagedGridResult = itemService.searchItemByThirdCatPage(catId, sort, page, pageSize);
        return JSONResult.successNeedData(pagedGridResult);
    }

    @ApiOperation(value = "关键词查询商品",notes = "关键词查询商品",httpMethod = "GET")
    @GetMapping("/search")
    public JSONResult searchItemByKeywordsPage(
            @RequestParam(name = "keywords") String keywords,
            @RequestParam(name = "sort") String sort,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "20") Integer pageSize
    ){
        PagedGridResult pagedGridResult = itemService.searchItemByKeywordsPage(keywords, sort, page, pageSize);
        return JSONResult.successNeedData(pagedGridResult);
    }

    @ApiOperation(value = "商品评论内容",notes = "商品评论内容",httpMethod = "GET")
    @GetMapping("/comments")
    public JSONResult queryItemCommentContent(

            @RequestParam(value = "itemId") String itemId,
            @RequestParam(value = "level",defaultValue = "") Integer level,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
    ){
        if (itemId==null){
            return JSONResult.FailureNeedMessage(null);
        }
        PagedGridResult pagedGridResult = itemService.queryPageComments(itemId, level, page, pageSize);

        return JSONResult.successNeedData(pagedGridResult);
    }

    @ApiOperation(value = "商品评价数量",notes = "商品评价数量",httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JSONResult queryItemCommentCount(@RequestParam("itemId") String itemId){
        ItemsCommentsCountVO itemsCommentsCountVO = itemService.queryItemCommentCount(itemId);
        return JSONResult.successNeedData(itemsCommentsCountVO);
    }

    @ApiOperation(value = "显示商品详情信息",notes = "显示商品详情信息",httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONResult queryItemInfo(@PathVariable("itemId") String itemId ){
        Items items = itemService.queryItemDetailByItemId(itemId);
        ItemsParam itemsParam = itemService.queryItemParamByItemId(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImageByItemId(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecListByItemID(itemId);
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemParams(itemsParam);
        itemInfoVO.setItemImgList(itemsImgs);
        itemInfoVO.setItemSpecList(itemsSpecs);
        return JSONResult.successNeedData(itemInfoVO);
    }

}
