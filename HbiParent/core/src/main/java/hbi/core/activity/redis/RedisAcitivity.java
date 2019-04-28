package hbi.core.activity.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hand.hap.cache.impl.HashStringRedisCache;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.mapper.ActivityDemo1Mapper;
import hbi.core.demoPackage.dto.EmployeeDemo;
import hbi.core.demoPackage.mapper.EmployeeDemoMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/4/19 9:41
 * @Description:
 **/
public class RedisAcitivity extends HashStringRedisCache<List<ActivityDemo1>> {

    private String productSql = ActivityDemo1Mapper.class.getName() + ".selectAll";
    private Logger logger = LoggerFactory.getLogger(HashStringRedisCache.class);
    private final Long EXPIRE_TIME = 7 * 24 * 60 * 60L;

    @Override
    public void init() {
        super.init();
        //活动信息加入缓存
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            List<ActivityDemo1> ProductInfoList = sqlSession.selectList(productSql);
            List<ActivityDemo1> activityDemo1s = addRedis(ProductInfoList);
            setValue("activityRedis", activityDemo1s,EXPIRE_TIME);
        }
    }


    public List<ActivityDemo1> addRedis(List<ActivityDemo1> productInfoList){
        List<ActivityDemo1> activityList=new ArrayList<>();
        for (ActivityDemo1 activityDemo1 : productInfoList) {
            if("IN_RELEASE".equals(activityDemo1.getActivitySataus())){
                activityList.add(activityDemo1);
            }
        }
        return activityList;
    }

    public List<ActivityDemo1> addRedisActivity(ActivityDemo1 productInfoList){
        List<ActivityDemo1> activityList=new ArrayList<>();
        activityList.add(productInfoList);
        return activityList;
    }

    public void setValue(String key, List<ActivityDemo1> productInfos,Long expireTime) {
        try {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":" + key.toLowerCase());
            String value = getObjectMapper().writeValueAsString(productInfos);
            getRedisTemplate().execute((RedisCallback<Object>) (connection) -> {
                connection.setEx(keyBytes, expireTime, strSerializer.serialize(value));
                return null;
            });
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
    }


    @Override
    public List<ActivityDemo1> getValue(String key) {
        return getRedisTemplate().execute((RedisCallback<List<ActivityDemo1>>) (connection) -> {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":" + key.toLowerCase());
            byte[] valueBytes = connection.get(keyBytes);
            try {
                if (null != valueBytes)
                    return (List<ActivityDemo1>) getObjectMapper().readValue(valueBytes, Object.class);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        });
    }

}
