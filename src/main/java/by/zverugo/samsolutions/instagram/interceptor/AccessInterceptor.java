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
import javax.servlet.http.HttpSession;

public class AccessInterceptor extends HandlerInterceptorAdapter {
    private final Logger LOGGER = Logger.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOGGER.info("AccessInterceptor.preHandle start  " + o);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority roleAuthority = (GrantedAuthority) authentication.getAuthorities().toArray()[0];
        if (UserRoleEnum.ADMIN.getRole().equals(roleAuthority.getAuthority())) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin");
            return false;
        } else if (UserRoleEnum.USER.getRole().equals(roleAuthority.getAuthority())) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/users/user");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
}
