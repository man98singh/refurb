package com.houstondirectauto.refurb.service;

import static com.houstondirectauto.refurb.util.Constants.EMAIL_ALREADY_EXIST;
import static com.houstondirectauto.refurb.util.Constants.USER_NOT_EXIST;

import java.util.*;

import com.houstondirectauto.refurb.entity.RoleEntity;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.repository.UserRepository;
import com.houstondirectauto.refurb.request.UpdateUserRequest;
import com.houstondirectauto.refurb.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
//	private final UserModulePermissionRepository userModulePermissionRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    /**
     * Constructor
     *
     * @param userRepository
     */

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        super();
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserEntity user = optionalUser.get();
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    /**
     * Create User in Database
     *
     * @param userEntity userEntity.
     * @return UserEntity userEntity.
     */
    public UserEntity createUser(UserEntity userEntity) throws BadRequestException {
        Optional<UserEntity> user = userRepository.findByEmail(userEntity.getEmail());
        if (user.isPresent()) {
            log.error("email id Already exist.");
            throw new BadRequestException(EMAIL_ALREADY_EXIST);
        }
        userEntity.setPassword(bcryptEncoder.encode(userEntity.getPassword()));

        log.info("aaaaaaaaaaaa");
        log.info(userEntity.getPassword());

        return userRepository.save(userEntity);

    }

//	/**
//	 * public UserEntity createUser(UserEntity userEntity) throws
//	 * BadRequestException { UserEntity userEntity =
//	 * MapperUtil.convertModelToEntity(createUserRequest, UserEntity.class);
//	 *
//	 * UserEntity user = userRepository.findByEmail(userEntity.getEmail()); if
//	 * (Objects.nonNull(user)) { log.error("email id Already exist."); throw new
//	 * BadRequestException(EMAIL_ALREADY_EXIST); }
//	 * userEntity.setPassword(bcryptEncoder.encode(userEntity.getPassword()));
//	 * return userRepository.save(userEntity);
//	 *
//	 * }
//	 */
//	/**
//	 * UpdateUser
//	 *
//	 * @param userEntity
//	 * @return
//	 */
//	public UserEntity updateUser(UserEntity userEntity) {
//		return userRepository.save(userEntity);
//	}
//

    /**
     * Update existing user in Database
     *
     * @param userId
     * @return UserEntity
     * @throws EntityNotFoundException
     */
    public UserEntity updateUser(Integer userId, UpdateUserRequest updateUserRequest) throws EntityNotFoundException {
        UserEntity userData = findById(userId);
        if (Objects.nonNull(updateUserRequest.getFirstName())) {
            userData.setFirstName(updateUserRequest.getFirstName());
        }
        if (Objects.nonNull(updateUserRequest.getLastName())) {
            userData.setLastName(updateUserRequest.getLastName());
        }

        if (Objects.nonNull(updateUserRequest.getImage())) {
            userData.setImage(updateUserRequest.getImage());
        }
        if (Objects.nonNull(updateUserRequest.getPhoneNumber())) {
            userData.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }

        if (Objects.nonNull(updateUserRequest.getLocation())) {
            userData.setLocation(updateUserRequest.getLocation());
        }

        if (Objects.nonNull(updateUserRequest.getImage())) {
            userData.setImage(updateUserRequest.getImage());
        }
        if (Objects.nonNull(updateUserRequest.getIdmsId())) {
            userData.setIdmsId(updateUserRequest.getIdmsId());
        }
        if (Objects.nonNull(updateUserRequest.getSmartSheetId())) {
            userData.setSmartSheetId(updateUserRequest.getSmartSheetId());
        }
        if (Objects.nonNull(updateUserRequest.getRfIdBadgeId())) {
            userData.setRfIdBadgeId(updateUserRequest.getRfIdBadgeId());
        }
        if (Objects.nonNull(updateUserRequest.getPinCode())) {
            userData.setPinCode(updateUserRequest.getPinCode());
        }

        if (Objects.nonNull(updateUserRequest.getRoleId()) && updateUserRequest.getRoleId() != 0) {
            RoleEntity roleEntity = new RoleEntity();
            Set<RoleEntity> set = new HashSet<RoleEntity>();
            set.add(roleService.findById(updateUserRequest.getRoleId()));
            userData.setRoles(set);
        }
        return userRepository.save(userData);
    }

//	/**
//	 * Update existing user in Database
//	 *
//	 * @param userId
//	 * @param userEntity
//	 * @return UserEntity
//	 * @throws EntityNotFoundException
//	 */
//	public UserEntity updateUserInfo(Integer userId, UserEntity userEntity) throws EntityNotFoundException {
//		UserEntity userData = findById(userId);
//		if (Objects.nonNull(userEntity.getFirstName())) {
//			userData.setFirstName(userEntity.getFirstName());
//		}
//		if (Objects.nonNull(userEntity.getLastName())) {
//			userData.setLastName(userEntity.getLastName());
//		}
//
//		if (Objects.nonNull(userEntity.getImage())) {
//			userData.setImage(userEntity.getImage());
//		}
//		if (Objects.nonNull(userEntity.getContactNumber())) {
//			userData.setContactNumber(userEntity.getContactNumber());
//		}
//
//		if (Objects.nonNull(userEntity.getPassword())) {
//			userData.setPassword(bcryptEncoder.encode(userEntity.getPassword()));
//		}
//
//		return userRepository.save(userData);
//	}
//
//
//	private void getAnddeleteByUserId(Integer userId) {
//		List<Integer> ids = userModulePermissionRepository.getAllIds(userId);
//		if(!ids.isEmpty()) {
//			userModulePermissionRepository.deleteAllById(ids);
//		}
//
//
//	}
//	/**
//	 * Update existing user in Database
//	 *
//	 * @param userId
//	 * @param userEntity
//	 * @return UserEntity
//	 * @throws EntityNotFoundException
//	 */
//	public UserEntity updatePasword(Integer userId, String password) throws EntityNotFoundException {
//
//		UserEntity userData = findById(userId);
//		if (Objects.nonNull(password)) {
//			userData.setPassword(bcryptEncoder.encode(password));
//		}
//		return userRepository.save(userData);
//	}
//

    /**
     * Find user in Database
     *
     * @param userId
     * @return UserEntity
     * @throws EntityNotFoundException
     */
    public UserEntity findById(Integer userId) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (Objects.isNull(userEntity)) {
            log.error("User with id {} not found.", userId);
            throw new EntityNotFoundException(Constants.USERS_NOT_EXIST);
        }
        return userEntity;
    }

    /**
     * Find user in Database
     *
     * @param username
     * @return UserEntity
     * @throws EntityNotFoundException
     */
    public UserEntity findByUsername(String username) throws EntityNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        log.error("User with id {} not found.", username);
        throw new EntityNotFoundException(USER_NOT_EXIST);
    }

//	/**
//	 * Check email already exists in Database, if found true or false
//	 *
//	 * @param emailId String value
//	 * @return Boolean value
//	 */
//	public Boolean isUserExists(String emailId) {
//		UserEntity userEntity = userRepository.findByEmail(emailId);
//		return Objects.nonNull(userEntity) ? Boolean.TRUE : Boolean.FALSE;
//	}
//
//	/**
//	 * Find user in Database
//	 *
//	 * @param userId
//	 * @return UserEntity
//	 * @throws EntityNotFoundException
//	 */
//	public UserEntity findByToken(int otp) throws EntityNotFoundException {
//		UserEntity userEntity = userRepository.findByToken(otp).orElse(null);
//		if (Objects.isNull(userEntity)) {
//			log.error("Constants.INVALID OTP");
//			throw new EntityNotFoundException(INVALID_OTP);
//		}
//		return userEntity;
//	}

    /**
     * Get Users.
     *
     * @return User List.
     */
    public Page<UserEntity> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Delete existing user from Database
     *
     * @param id
     * @throws BadRequestException
     * @throws EntityNotFoundException
     */
    public void deleteUser(Integer id) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            userRepository.deleteById(id);
        }
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }


//	public List<UserModuleProjection> getAllUserModules(Integer id){
//		return userModulePermissionRepository.getAllUserModules(id);
//	}


}
