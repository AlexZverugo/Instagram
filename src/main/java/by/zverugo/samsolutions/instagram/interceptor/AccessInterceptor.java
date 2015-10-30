package by.zverugo.samsolutions.instagram.interceptor;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("AccessInterceptor.preHandle start  " + o);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority roleAuthority = (GrantedAuthority) authentication.getAuthorities().toArray()[0];
        if (roleAuthority.getAuthority().equals(UserRoleEnum.ADMIN.getRole())) {
            httpServletResponse.sendRedirect("/admin");
            return false;
        } else if (roleAuthority.getAuthority().equals(UserRoleEnum.USER.getRole())) {
            httpServletResponse.sendRedirect("/users/user");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
}
