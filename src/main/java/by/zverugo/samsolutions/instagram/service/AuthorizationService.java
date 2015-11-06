package by.zverugo.samsolutions.instagram.service;

import by.zverugo.samsolutions.instagram.dao.user.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service("authorizationService")
@Transactional(readOnly = true)
public class AuthorizationService implements UserDetailsService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDAO;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        by.zverugo.samsolutions.instagram.entity.User user = userDAO.getUserByName(username);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        User userLog = new User(
                user.getLogin(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user.getRole())
        );

        LOGGER.info(messageSource.getMessage("service.authorizationService.loadUserByUsername",
                new Object[]{username}, Locale.ENGLISH));

        return userLog;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }

    public void login(String username, String password) {
        Authentication token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOGGER.info(messageSource.getMessage("service.authorizationService.login",
                new Object[]{username}, Locale.ENGLISH));
    }

}
