package com.bullpick.stock.screener;

import static org.assertj.core.api.Assertions.assertThat;

import com.bullpick.stock.screener.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    //creating new user
    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("newuser@gmail.com");
        user.setPassword("password123");
        user.setFirstName("First");
        user.setLastName("Last");

        User savedUser = repo.save(user);

        //assert test using entity manager
        User userExists = entityManager.find(User.class, savedUser.getId());

        assertThat(userExists.getEmail()).isEqualTo(user.getEmail());
    }

    //find user by email method
    @Test
    public void testFindUserByEmail() {
        String email = "newuser@gmail.com";

        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
