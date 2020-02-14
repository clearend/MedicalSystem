package com.example.medicalsystem.common.aop;

import com.example.medicalsystem.common.utils.IpUtil;
import com.google.gson.Gson;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口情况记录
 * @author zuozhiwei
 */
public class PerformanceInterceptor extends HandlerInterceptorAdapter {

    private static ThreadLocal<StopWatch> local = new ThreadLocal<StopWatch>();

    private final Logger switchLogger = LoggerFactory.getLogger("org.perf4j.TimingLogger");
    private static Logger LOGGER = LoggerFactory.getLogger(PerformanceInterceptor.class);
    Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> enumeration = request.getHeaderNames();
        Enumeration<String> parameterNames = request.getParameterNames();
        if (enumeration != null) {
            Map<String, String> headerMap = new HashMap();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                headerMap.put(key, request.getHeader(key));
            }
            LOGGER.info("请求接口:{} requestHeader:{}", request.getRequestURI(), gson.toJson(headerMap));
        }
        if (parameterNames != null) {
            Map<String, String> paramMap = new HashMap();
            while (parameterNames.hasMoreElements()) {
                String key = parameterNames.nextElement();
                paramMap.put(key, request.getHeader(key));
            }
            LOGGER.info("接口:[{}] 请求参数:[{}]", request.getRequestURI(), gson.toJson(paramMap));
        }
        StopWatch stopWatch = new Slf4JStopWatch("shell");
        local.set(stopWatch);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        StopWatch watch = local.get();
        if (watch != null) {
            watch.stop(generateOperatonIdendifier(request, watch.getElapsedTime()));
            LOGGER.info("接口耗时 url:{} 耗时:{}ms", request.getRequestURI(), watch.getElapsedTime());
            local.remove();
        }
    }

    private String generateOperatonIdendifier(HttpServletRequest request, long exeTime) {
        StringBuilder sb = new StringBuilder(64);

        sb.append(request.getMethod()).append("|");

        // URI
        if (switchLogger.isTraceEnabled()) { // 如果是trace级别，统计到具体的URI
            sb.append(request.getRequestURL());
            sb.append('|');
            String clientIp = IpUtil.getIp(request);
            sb.append(clientIp);
            sb.append('|');
            sb.append(request.getHeader("User-Agent"));
        } else { // 按URI pattern匹配，方便汇总
            sb.append(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));
        }

        // 记录慢得url,
        if (exeTime > 100) {
            sb.append("|SLOW");
            // TODO 慢查询打印问题
            LOGGER.warn("接口 url:{} 慢查询:{}", request.getRequestURI(), sb);
        }

        return sb.toString();
    }

}
