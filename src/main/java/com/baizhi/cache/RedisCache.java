package com.baizhi.cache;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Configuration
@Aspect
public class RedisCache {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Around("execution(* com.baizhi.service.*.get*(..))")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("==环绕通知==");
        /*序列化解决乱码*/
        StringRedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);

        //获取类上的注解
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();

        //判断该类上是否有AddCache注解
        boolean annotationPresent = method.isAnnotationPresent(AddCache.class);
        //判断
        if(annotationPresent){
            //有添加缓存的注解

            //获取可变长字符串
            StringBuilder sb = new StringBuilder();

            //获取类的全限定名   包名.类名
            String clazzNameKey = proceedingJoinPoint.getTarget().getClass().getName();

            //获取方法名
            String methodName = proceedingJoinPoint.getSignature().getName();
            sb.append(methodName);

            //获取实参
            Object[] args = proceedingJoinPoint.getArgs();
            for (Object arg : args) {
                sb.append(arg);
            }

            //设置一个 key
            //拼接好的key   方法名+实参
            String key = sb.toString();

            //操作Hash 类型
            HashOperations hashOperations = redisTemplate.opsForHash();

            //判断key在缓存中是否存在
            Boolean aBoolean = hashOperations.hasKey(clazzNameKey, key);

            //声明结果
            Object result =null;

            //判断
            if(aBoolean){
                //存在  从缓存中取数据  并返回结果
                result = hashOperations.get(clazzNameKey,key);
            }else{

                result = proceedingJoinPoint.proceed();

                //不存在   将数据加入缓存 并返回结果
                hashOperations.put(clazzNameKey,key,result);
            }
            return result;
        }else{
            //没有添加缓存的注解
            Object result = proceedingJoinPoint.proceed();
            return result;
        }
    }

    //增删改清除缓存
    @After("execution(* com.baizhi.service.*.*(..)) && !execution(* com.baizhi.service.*.get*(..))")
    public void after(JoinPoint joinPoint){

        System.out.println("===清除缓存===");

        //清空切到的这个类中所有的缓存
        String clazzNameKey = joinPoint.getTarget().getClass().getName();

        //清除本类的缓存
        stringRedisTemplate.delete(clazzNameKey);

    }

}
