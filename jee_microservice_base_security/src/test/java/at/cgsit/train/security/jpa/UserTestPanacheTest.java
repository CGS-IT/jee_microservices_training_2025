package at.cgsit.train.security.jpa;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserTestPanacheTest {

    @Test
    @Transactional
    void testAddUserWithHashedPassword() {
        // when
        User.add("alice", "secret123", "user_role");

        // then
        User user = User.find("username", "alice").firstResult();
        assertNotNull(user);
        assertEquals("alice", user.username);
        assertEquals("user_role", user.role);
        assertNotEquals("secret123", user.password); // must be hashed
        assertTrue(user.password.startsWith("$2a$")); // bcrypt prefix
    }

    @Test
    @Transactional
    void testDeleteUser() {
        User.add("bob", "pwd", "admin_role");
        User user = User.find("username", "bob").firstResult();
        assertNotNull(user);

        user.delete();
        assertNull(User.find("username", "bob").firstResult());
    }

    @Test
    @Transactional
    void testMultipleUserRoles() {
        User.add("multi", "secret", "admin,user,editor");
        User user = User.find("username", "multi").firstResult();

        assertNotNull(user);
        assertTrue(user.role.contains("admin"));
        assertTrue(user.role.contains("user"));
        assertTrue(user.role.contains("editor"));
    }
}
