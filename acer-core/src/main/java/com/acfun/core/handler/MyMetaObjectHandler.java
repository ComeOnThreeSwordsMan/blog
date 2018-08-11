package com.acfun.core.handler;

import com.acfun.core.util.SessionUtil;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis公共字段自动填充
 * @author fqlee
 * @date: created in 10:23 2017/12/16
 */
@Component
@Slf4j
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * description 插入自动填充
     *
     * @author fqlee
     * @date   10:30 2017/12/16
     * @param metaObject pojo
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("########################mybatis-plus新增自动填充开始########################");
        String userName;
        try {
            userName = SessionUtil.getUserName();
        } catch (Exception e) {
            userName = "";
            log.warn("mybatis-plus新增自动填充未获取到userName");
        }
        if(StringUtils.isEmpty(userName)){
            userName = "";
            log.warn("mybatis-plus修改自动填充未获取到userName");
        }
        thisSetFieldValByName("createdDate", new Date(), metaObject);
        //thisSetFieldValByName("createdBy", userId, metaObject);
        thisSetFieldValByName("lastModifiedDate", new Date(), metaObject);
        thisSetFieldValByName("lastModifiedBy", userName, metaObject);
        log.info("########################mybatis-plus新增自动填充结束########################");
    }

    /**
     * description 更新自动填充
     *
     * @author fqlee
     * @date   10:30 2017/12/16
     * @param metaObject pojo
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("########################mybatis-plus修改自动填充开始########################");
        String userName;
        try {
            userName = SessionUtil.getUserName();
        } catch (Exception e) {
            userName = "";
            log.warn("mybatis-plus修改自动填充未获取到userName",e.getMessage());
        }
        if(StringUtils.isEmpty(userName)){
            userName = "";
            log.warn("mybatis-plus修改自动填充未获取到userName");
        }
        thisSetFieldValByName("lastModifiedDate", new Date(), metaObject);
        thisSetFieldValByName("lastModifiedBy", userName, metaObject);
        log.info("########################mybatis-plus修改自动填充结束########################");
    }

    public MetaObjectHandler thisSetFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {
        if (metaObject.hasSetter(fieldName) &&
                metaObject.hasGetter(fieldName)) {
            metaObject.setValue(fieldName, fieldVal);
        } else if (metaObject.hasGetter(META_OBJ_PREFIX) &&
                StringUtils.checkValNotNull(metaObject.getValue(META_OBJ_PREFIX)) &&
                metaObject.hasSetter(META_OBJ_PREFIX + "." + fieldName)&&
                metaObject.hasGetter(META_OBJ_PREFIX + "." + fieldName)) {
            metaObject.setValue(META_OBJ_PREFIX + "." + fieldName, fieldVal);
        }
        return this;
    }
}
