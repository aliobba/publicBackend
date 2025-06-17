package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.request.CreateAnimatorRequest;
import com.ooredoo.report_builder.dto.request.UpdateAnimatorRequest;
import com.ooredoo.report_builder.dto.response.AnimatorResponse;
import com.ooredoo.report_builder.dto.response.POSSummaryDTO;
import com.ooredoo.report_builder.dto.response.UserSummaryDTO;
import com.ooredoo.report_builder.entity.Animator;
import com.ooredoo.report_builder.entity.AnimatorRole;
import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.mapper.AnimatorMapper;
import com.ooredoo.report_builder.repo.AnimatorRepository;
import com.ooredoo.report_builder.repo.AnimatorRoleRepository;
import com.ooredoo.report_builder.repo.POSRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnimatorService {

    private final AnimatorRepository animatorRepository;
    private final AnimatorRoleRepository animatorRoleRepository;
    private final UserRepository userRepository;
    private final POSRepository posRepository;
    private final AnimatorMapper animatorMapper;

    public AnimatorService(AnimatorRepository animatorRepository, 
                          AnimatorRoleRepository animatorRoleRepository,
                          UserRepository userRepository,
                          POSRepository posRepository,
                          AnimatorMapper animatorMapper) {
        this.animatorRepository = animatorRepository;
        this.animatorRoleRepository = animatorRoleRepository;
        this.userRepository = userRepository;
        this.posRepository = posRepository;
        this.animatorMapper = animatorMapper;
    }

    public AnimatorResponse createAnimator(CreateAnimatorRequest request) {
        // Check if PIN already exists
        if (animatorRepository.existsByPin(request.getPin())) {
            throw new RuntimeException("Animator with PIN " + request.getPin() + " already exists");
        }

        // Create new animator entity using mapper
        Animator animator = animatorMapper.toEntity(request);

        // Set role
        AnimatorRole role = animatorRoleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("AnimatorRole not found with id: " + request.getRoleId()));
        animator.setRole(role);

        // Set POS if provided
        if (request.getPosId() != null) {
            POS pos = posRepository.findById(request.getPosId())
                    .orElseThrow(() -> new ResourceNotFoundException("POS not found with id: " + request.getPosId()));
            animator.setPos(pos);
        }

        // Set users if provided
        if (request.getUserIds() != null && !request.getUserIds().isEmpty()) {
            Set<User> users = new HashSet<>();
            for (Integer userId : request.getUserIds()) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
                users.add(user);
            }
            animator.setUsers(users);
        }

        // Save and return response
        Animator savedAnimator = animatorRepository.save(animator);
        return animatorMapper.toResponse(savedAnimator);
    }

    public AnimatorResponse updateAnimator(Integer id, UpdateAnimatorRequest request) {
        // Find existing animator
        Animator animator = animatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animator not found with id: " + id));

        // Update fields
        if (request.getPin() != null) {
            animator.setPin(request.getPin());
        }
        
        if (request.getDescription() != null) {
            animator.setDescription(request.getDescription());
        }

        // Update role if provided
        if (request.getRoleId() != null) {
            AnimatorRole role = animatorRoleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("AnimatorRole not found with id: " + request.getRoleId()));
            animator.setRole(role);
        }

        // Update POS if provided
        if (request.getPosId() != null) {
            POS pos = posRepository.findById(request.getPosId())
                    .orElseThrow(() -> new ResourceNotFoundException("POS not found with id: " + request.getPosId()));
            animator.setPos(pos);
        }

        // Update users if provided
        if (request.getUserIds() != null) {
            Set<User> users = new HashSet<>();
            for (Integer userId : request.getUserIds()) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
                users.add(user);
            }
            animator.setUsers(users);
        }

        // Save and return response
        Animator updatedAnimator = animatorRepository.save(animator);
        return animatorMapper.toResponse(updatedAnimator);
    }

    public void deleteAnimator(Integer id) {
        Animator animator = animatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animator not found with id: " + id));
        animatorRepository.delete(animator);
    }

    public List<AnimatorResponse> getAllAnimators() {
        return animatorRepository.findAll().stream()
                .map(animatorMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AnimatorResponse getAnimatorById(Integer id) {
        return animatorRepository.findById(id)
                .map(animatorMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Animator not found with id: " + id));
    }

    public List<AnimatorResponse> getAnimatorsByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return animatorRepository.findByUsers(user).stream()
                .map(animatorMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AnimatorResponse> getAnimatorsByPosId(Integer posId) {
        return animatorRepository.findByPosId(posId).stream()
                .map(animatorMapper::toResponse)
                .collect(Collectors.toList());
    }


}