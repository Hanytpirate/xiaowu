package com.ruoyi.common.datascope.aspect;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.BaseEntity;

import com.ruoyi.common.datascope.annotation.MyDataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDataScopeAspect {

    public static final String DATA_SCOPE = "dataScope";


    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, MyDataScope controllerDataScope) throws Throwable
    {
        clearDataScope(point);
        handleDataScope(point, controllerDataScope);
    }
    private void handleDataScope(final JoinPoint joinPoint, MyDataScope controllerDataScope)
    {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();

        if (StringUtils.isNotNull(loginUser))
        {

            SysUser currentUser = loginUser.getSysUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin())
            {
                dataScopeFilter(joinPoint, currentUser,  controllerDataScope.alias());
            }
        }
    }

    private void dataScopeFilter(JoinPoint joinPoint, SysUser user,String alias) {
        Long userId = user.getUserId();
        StringBuilder sqlString = new StringBuilder();
        sqlString.append(StringUtils.format("AND {}.team_id IN (SELECT team_id FROM xiaowu_team_user WHERE user_id = {})",alias,userId));
        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseEntity)
            {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
            }
        }
    }

    private void clearDataScope(final JoinPoint joinPoint)
    {
        Object params = joinPoint.getArgs()[0];
        if (StringUtils.isNotNull(params) && params instanceof BaseEntity)
        {
            BaseEntity baseEntity = (BaseEntity) params;
            baseEntity.getParams().put(DATA_SCOPE, "");
        }
    }
}
