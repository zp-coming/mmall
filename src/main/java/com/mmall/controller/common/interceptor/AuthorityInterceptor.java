package com.mmall.controller.common.interceptor;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 对后台管理员的权限拦截控制,对其他用户的拦截可以在dispatcher-servlet.xml 配置
 */
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        // 请求中controller的方法名
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 解析HandlerMethod
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();
        // 解析具体参数，具体的参数key和value是什么
        StringBuilder requestParamBuffer = new StringBuilder();
        Map paramMap = request.getParameterMap();
        Iterator it = paramMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String mapKey = (String) entry.getKey();
            String mapValue = StringUtils.EMPTY;
            Object obj = entry.getValue();
            if (obj instanceof String[]) {
                String[] strs = (String[])obj;
                mapValue = Arrays.toString(strs);
            }
            requestParamBuffer.append(mapKey).append("=").append(mapValue);
        }

        // 灵活控制需要拦截的请求，通过methodName 和 className
        if (StringUtils.equals(className, "UserManagerController") && StringUtils.equals(methodName, "login")) {
            // 拦截到login.do，要放行
            // 拦截到登录请求，不能打印参数到日志，会有密码，防止日志泄漏
            log.info("权限拦截器拦截到请求，className:{}, methodName:{}", className, methodName);
            return true;
        }
        log.info("权限拦截器拦截到请求，className:{}, methodName:{}, param:{}", className, methodName, requestParamBuffer.toString());
        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotEmpty(loginToken)) {
            String userJsonStr = RedisShardedPoolUtil.get(loginToken);
            user = JsonUtil.string2Obj(userJsonStr, User.class);
        }
        if (user == null || (user.getRole() != Const.Role.ROLE_ADMIN)) {
            // 返回false, 即不会调用controller的方法，直接从这里返回数据给客户端，要重置response
            response.reset(); // 要添加reset,否则会报异常 getWriter() has already been called for this response
            response.setCharacterEncoding("UTF-8"); // 不设置会乱码
            response.setContentType("application/json;charset=UTF-8"); // 设置为统一的返回数据类型
            PrintWriter out = response.getWriter();
            if (user == null) {
                out.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器已拦截，用户未登录")));
            } else {
                out.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器已拦截，用户无权限操作")));
            }
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
    }
}
