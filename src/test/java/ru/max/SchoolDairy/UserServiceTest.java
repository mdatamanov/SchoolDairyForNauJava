package ru.max.SchoolDairy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import ru.max.SchoolDairy.dto.Role;
import ru.max.SchoolDairy.model.User;
import ru.max.SchoolDairy.repository.user.UserRepository;
import ru.max.SchoolDairy.service.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;
    private User testUser;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, null);
        ReflectionTestUtils.setField(userService, "passwordEncoder", passwordEncoder);

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("12345");
        testUser.setRole(Role.ROLE_USER);
    }

    @Test
    void createUser_Success() throws Exception {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("12345")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        userService.createUser(testUser);

        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("12345");
    }

    @Test
    void createUser_UserAlreadyExists_ThrowsException() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        Exception exception = assertThrows(Exception.class, () -> {
            userService.createUser(testUser);
        });

        assertEquals("User with this username already exists", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void findUserById_UserExists_ReturnsUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        Optional<User> found = userService.findUserById(1L);

        assertTrue(found.isPresent());
        assertEquals("testuser", found.get().getUsername());
    }

    @Test
    void findUserById_UserDoesNotExist_ReturnsEmptyOptional() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<User> found = userService.findUserById(999L);
        assertFalse(found.isPresent());
    }

    @Test
    void deleteUser_Success() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateLogin_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        userService.updateLogin(1L, "newusername");

        assertEquals("newusername", testUser.getUsername());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void updateLogin_UserDoesNotExist_DoesNothing() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        userService.updateLogin(999L, "newusername");

        verify(userRepository, never()).save(any(User.class));
    }
}