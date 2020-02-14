package com.example.medicalsystem.common.aop;

import com.google.gson.Gson;
import com.example.medicalsystem.common.ApiResponse;
import com.example.medicalsystem.common.CommonConstant;
import com.example.medicalsystem.common.ErrorCodeEnum;
import com.example.medicalsystem.common.annotation.AccessToken;
import com.example.medicalsystem.common.annotation.IgnoreAccessToken;
import com.example.medicalsystem.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.example.medicalsystem.user.repository.User;
import com.example.medicalsystem.user.repository.UserRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * token验证
 *
 */
public class AccessTokenInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(AccessTokenInterceptor.class);

	@Resource
	private UserRepository userRepository;

	Gson gson = new Gson();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod hm = (HandlerMethod) handler;
		if (hm == null) {
			return true;
		}
		Class<?> clazz = hm.getBeanType();
		boolean isClzAnnotation = clazz.isAnnotationPresent(AccessToken.class);
		if (isClzAnnotation) {
			// 处理忽略token的情况，如果有token补充用户信息
			Method method = hm.getMethod();
			if (method != null) {
				IgnoreAccessToken ignoreAccessToken = method.getAnnotation(IgnoreAccessToken.class);
				if (ignoreAccessToken != null) {
					return true;
				}
			}
			try {
				validateToken(request);
				return true;
			} catch (Exception e) {
				String error = gson.toJson(ApiResponse.error(ErrorCodeEnum.INVALID_TOKEN));

				PrintWriter writer = null;
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=utf-8");
				writer = response.getWriter();
				writer.print(error);
				return false;
			}
		}

		Method method = hm.getMethod();
		if (method == null) {
			return true;
		}

		AccessToken accessToken = method.getAnnotation(AccessToken.class);
		if (accessToken == null) {
			return true;
		}
		try {
			validateToken(request);
			return true;
		} catch (Exception e) {
			String error = gson.toJson(ApiResponse.error(ErrorCodeEnum.INVALID_TOKEN));

			PrintWriter writer = null;
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			writer = response.getWriter();
			writer.print(error);
			return false;
		}
	}

	/**
	 * 验证token
	 *
	 * @param request
	 * @return
	 */
	private boolean validateToken(HttpServletRequest request) {
		String token = request.getParameter("token");
		if (Objects.isNull(token) || "".equals(token)) {
			logger.warn("validateToken token为空");
			throw new BizException(ErrorCodeEnum.INVALID_TOKEN);
		}

		User user = userRepository.findByToken(token);
		if (Objects.isNull(user)) {
			logger.warn("validateToken token验证失败 token:{}", token);
			throw new BizException(ErrorCodeEnum.INVALID_TOKEN);
		}
        request.setAttribute(CommonConstant.LOGIN_USER, user);
        logger.info("validateToken 登录用的信息 token:{} userInfo:{}", token, gson.toJson(user));
		return true;
	}
}
