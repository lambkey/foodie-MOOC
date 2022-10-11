package lamb.key.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lamb.key.pojo.Carousel;
import lamb.key.pojo.Category;
import lamb.key.pojo.vo.CategoryCustomVO;
import lamb.key.pojo.vo.NewsItemVO;
import lamb.key.service.CarouselService;
import lamb.key.service.CategoryService;
import lamb.key.utils.JSONResult;
import lamb.key.utils.enums.CarouselIsShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/18 16:44
 * @Version 1.0
 */
@Api(value = "首页",tags ="用于展示首页详情" )
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据",notes = "查询每个一级分类下的最新6条商品数据",httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootId}")
    public JSONResult queryNewsItemCommodityOfRootCategory(@PathVariable("rootId") Integer rootId){

        if (rootId == null){
            return JSONResult.FailureNeedMessage("分类不存在");
        }
        List<NewsItemVO> newsItemVOList = categoryService.queryNewsItemCommodityOfRootCategory(rootId);

        return JSONResult.successNeedData(newsItemVOList);

    }


    @ApiOperation(value = "所有子分类",notes = "根据root分类id查询该分类下的所有子分类",httpMethod = "GET")
    @GetMapping("/subCat/{rootId}")
    public JSONResult queryFatherAndKidCategory(@PathVariable("rootId") Integer rootId){
        if (rootId==null){
            return JSONResult.FailureNeedMessage("分类不存在");
        }

        List<CategoryCustomVO> categoryCustomVOList = categoryService.queryFatherAndKidCategory(rootId);

        return JSONResult.successNeedData(categoryCustomVOList);
    }


    @ApiOperation(value = "商品最大分类",notes = "获取商品最大分类数据root",httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult queryRootCategory(){

        List<Category> categoryList = categoryService.queryRootCategory();

        return JSONResult.successNeedData(categoryList);
    }


    @ApiOperation(value = "轮播图",notes = "获取轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult getAllIsShowCarousel(){
        List<Carousel> carouselList = carouselService.getCarousel(CarouselIsShow.isShow.type);
        return JSONResult.successNeedData(carouselList);
    }
}
