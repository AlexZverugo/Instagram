import by.zverugo.samsolutions.instagram.dto.UserDTO;
import by.zverugo.samsolutions.instagram.hash.PasswordHashEncoder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import by.zverugo.samsolutions.instagram.service.user.UserService;
import by.zverugo.samsolutions.instagram.util.enums.UserRoleEnum;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
@Transactional
public class UserTest {

    private UserDTO user;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordHashEncoder passwordHashEncoder;


    @Before
    public  void init() {
        user = new UserDTO();
        user.setId((long) 22);
        user.setLogin("user3");
        user.setPassword(passwordHashEncoder.encodePassword("123456",new Object()));
        user.setEmail("user@gmail.com");
        user.setRole(UserRoleEnum.USER);
    }


    @Test
    @Rollback(true)
    public void testAddUser() {
        userService.saveUser(user);
    }

}
