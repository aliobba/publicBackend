package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.request.CreateAnimatorRequest;
import com.ooredoo.report_builder.dto.request.UpdateAnimatorRequest;
import com.ooredoo.report_builder.dto.response.AnimatorResponse;
import com.ooredoo.report_builder.entity.Animator;
import com.ooredoo.report_builder.entity.AnimatorRole;
import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.AnimatorRepository;
import com.ooredoo.report_builder.repo.AnimatorRoleRepository;
import com.ooredoo.report_builder.repo.POSRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimatorServiceTest {

    @Mock
    private AnimatorRepository animatorRepository;

    @Mock
    private AnimatorRoleRepository animatorRoleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private POSRepository posRepository;

    @InjectMocks
    private AnimatorService animatorService;

    private Animator testAnimator;
    private AnimatorRole testRole;
    private User testUser;
    private POS testPOS;
    private CreateAnimatorRequest createRequest;
    private UpdateAnimatorRequest updateRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        testRole = new AnimatorRole();
        testRole.setId(1);
        testRole.setName("TEST_ROLE");
        testRole.setDescription("Test Role Description");

        testUser = new User();
        testUser.setId_user(1);
        testUser.setFirstname("Test");
        testUser.setLastname("User");
        testUser.setEmail("test@example.com");

        testPOS = new POS();
        testPOS.setId(1);
        testPOS.setName("Test POS");
        testPOS.setLocation("Test Location");

        testAnimator = new Animator();
        testAnimator.setId(1);
        testAnimator.setPin("1234");
        testAnimator.setDescription("Test Animator");
        testAnimator.setRole(testRole);
        testAnimator.setPos(testPOS);
        testAnimator.setCreatedAt(LocalDateTime.now());
        
        Set<User> users = new HashSet<>();
        users.add(testUser);
        testAnimator.setUsers(users);

        createRequest = new CreateAnimatorRequest();
        createRequest.setPin("5678");
        createRequest.setDescription("New Animator");
        createRequest.setRoleId(1);
        createRequest.setPosId(1);
        createRequest.setUserIds(Set.of(1));

        updateRequest = new UpdateAnimatorRequest();
        updateRequest.setPin("9012");
        updateRequest.setDescription("Updated Animator");
        updateRequest.setRoleId(1);
        updateRequest.setPosId(1);
        updateRequest.setUserIds(Set.of(1));
    }

    @Test
    void createAnimator_Success() {
        // Arrange
        when(animatorRepository.existsByPin(anyString())).thenReturn(false);
        when(animatorRoleRepository.findById(anyInt())).thenReturn(Optional.of(testRole));
        when(posRepository.findById(anyInt())).thenReturn(Optional.of(testPOS));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(animatorRepository.save(any(Animator.class))).thenReturn(testAnimator);

        // Act
        AnimatorResponse response = animatorService.createAnimator(createRequest);

        // Assert
        assertNotNull(response);
        assertEquals(testAnimator.getId(), response.getId());
        assertEquals(testAnimator.getPin(), response.getPin());
        verify(animatorRepository, times(1)).save(any(Animator.class));
    }

    @Test
    void createAnimator_PinExists_ThrowsException() {
        // Arrange
        when(animatorRepository.existsByPin(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> animatorService.createAnimator(createRequest));
        verify(animatorRepository, never()).save(any(Animator.class));
    }

    @Test
    void updateAnimator_Success() {
        // Arrange
        when(animatorRepository.findById(anyInt())).thenReturn(Optional.of(testAnimator));
        when(animatorRoleRepository.findById(anyInt())).thenReturn(Optional.of(testRole));
        when(posRepository.findById(anyInt())).thenReturn(Optional.of(testPOS));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(animatorRepository.save(any(Animator.class))).thenReturn(testAnimator);

        // Act
        AnimatorResponse response = animatorService.updateAnimator(1, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(updateRequest.getPin(), testAnimator.getPin());
        assertEquals(updateRequest.getDescription(), testAnimator.getDescription());
        verify(animatorRepository, times(1)).save(any(Animator.class));
    }

    @Test
    void updateAnimator_NotFound_ThrowsException() {
        // Arrange
        when(animatorRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> animatorService.updateAnimator(1, updateRequest));
        verify(animatorRepository, never()).save(any(Animator.class));
    }

    @Test
    void deleteAnimator_Success() {
        // Arrange
        when(animatorRepository.findById(anyInt())).thenReturn(Optional.of(testAnimator));

        // Act
        animatorService.deleteAnimator(1);

        // Assert
        verify(animatorRepository, times(1)).delete(any(Animator.class));
    }

    @Test
    void getAllAnimators_Success() {
        // Arrange
        List<Animator> animators = List.of(testAnimator);
        when(animatorRepository.findAll()).thenReturn(animators);

        // Act
        List<AnimatorResponse> responses = animatorService.getAllAnimators();

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(testAnimator.getId(), responses.get(0).getId());
    }

    @Test
    void getAnimatorById_Success() {
        // Arrange
        when(animatorRepository.findById(anyInt())).thenReturn(Optional.of(testAnimator));

        // Act
        AnimatorResponse response = animatorService.getAnimatorById(1);

        // Assert
        assertNotNull(response);
        assertEquals(testAnimator.getId(), response.getId());
        assertEquals(testAnimator.getPin(), response.getPin());
    }

    @Test
    void getAnimatorById_NotFound_ThrowsException() {
        // Arrange
        when(animatorRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> animatorService.getAnimatorById(1));
    }

    @Test
    void getAnimatorsByUserId_Success() {
        // Arrange
        List<Animator> animators = List.of(testAnimator);
        when(animatorRepository.findByUsersId(anyInt())).thenReturn(animators);

        // Act
        List<AnimatorResponse> responses = animatorService.getAnimatorsByUserId(1);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(testAnimator.getId(), responses.get(0).getId());
    }

    @Test
    void getAnimatorsByPosId_Success() {
        // Arrange
        List<Animator> animators = List.of(testAnimator);
        when(animatorRepository.findByPosId(anyInt())).thenReturn(animators);

        // Act
        List<AnimatorResponse> responses = animatorService.getAnimatorsByPosId(1);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(testAnimator.getId(), responses.get(0).getId());
    }
}