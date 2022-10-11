package lamb.key.config;

import lamb.key.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/7/29 13:31
 * @Version 1.0
 * 订单定时检查状态
 *
 */
//@Component
public class OrderJob {
    @Autowired
    private OrderService orderService;
    /*
    * 在这个网站有相对应的表达式: https://cron.qqe2.com/
    * */
    @Scheduled(cron = "0/3 * * * * ?")
    public void autoCloseOrder(){
        System.out.println("????");
        orderService.closeOrder();
    }
}
