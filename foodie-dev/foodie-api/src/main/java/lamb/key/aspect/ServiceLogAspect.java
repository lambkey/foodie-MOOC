package lamb.key.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/7/18 0:50
 * @Version 1.0
 *
 * AOP通知:
 *  1.前置通知,在方法调用之前执行
 *  2.后置通知,在方法正常调用之后执行
 *  3.环绕通知,在方法调用之前和之后，都分别可以执行通知
 *  4.异常通知,如果在方法调用过程中发生异常，则通知
 *  5.最终通知,在方法调用之后执行
 */
//@Aspect
//@Component
@Slf4j
public class ServiceLogAspect {

    /**
     *切面表达式
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 . 代表前面所有类下面的方法
     * 第六处 *(..) *代表类中的方法名 (..)表示方法中的任何参数
     *
     */
    @Around("execution(* lamb.key.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===== 开始执行 {}.{} =====",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        // 记录开始时间
        long beg = System.currentTimeMillis();

        // 执行目标service
        Object result = joinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();

        long consumeTime = end - beg;
        if (consumeTime>3000){
            log.error("===== 执行结束 耗时{}秒=====",consumeTime);
        }else if (consumeTime>2000){
            log.warn("===== 执行结束 耗时{}秒=====",consumeTime);
        }else {
            log.info("===== 执行结束 耗时{}秒=====",consumeTime);
        }
        return result;
    }
}
