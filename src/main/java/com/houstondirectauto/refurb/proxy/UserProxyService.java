package com.houstondirectauto.refurb.proxy;

import java.util.*;
import java.util.stream.Collectors;

import com.houstondirectauto.refurb.UserStatus;
import com.houstondirectauto.refurb.entity.RoleEntity;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.User;
import com.houstondirectauto.refurb.request.CreateUserRequest;
import com.houstondirectauto.refurb.request.UpdateUserRequest;
import com.houstondirectauto.refurb.service.UserService;
import com.houstondirectauto.refurb.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class UserProxyService {

	private final UserService userService;
//	private final MailService mailService;
//	private final AuthenticationManager authenticationManager;
//	private final JwtTokenUtil jwtTokenUtil;

	/**
	 * Constructor
	 */
//	@Autowired
//	public UserProxyService(UserService userService,MailService mailService,AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil) {
//		this.userService = userService;
//		this.mailService = mailService;
//		this.authenticationManager = authenticationManager;
//		this.jwtTokenUtil = jwtTokenUtil;
//	}

	@Autowired
	public UserProxyService(UserService userService) {
		this.userService = userService;
	}


	public CreateUserRequest createUser(CreateUserRequest createUserRequest) throws BadRequestException {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(createUserRequest.getEmail());
		userEntity.setFirstName(createUserRequest.getFirstName());
		userEntity.setLastName(createUserRequest.getLastName());
		userEntity.setPassword(createUserRequest.getPassword());
		userEntity.setPhoneNumber(createUserRequest.getPhoneNumber());
		userEntity.setLocation(createUserRequest.getLocation());
		userEntity.setImage(createUserRequest.getImage());
		userEntity.setIdmsId(createUserRequest.getIdmsId());
		userEntity.setSmartSheetId(createUserRequest.getSmartSheetId());
		userEntity.setRfIdBadgeId(createUserRequest.getRfIdBadgeId());
		userEntity.setPinCode(createUserRequest.getPinCode());
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(createUserRequest.getRoleId());
		Set<RoleEntity> set = new HashSet<RoleEntity>();
		set.add(roleEntity);
		userEntity.setRoles(set);
		userEntity.setUserStatus(UserStatus.ACTIVE);
		userService.createUser(userEntity);
		return createUserRequest;
	}

	/**
	 * Update existing user in Database
	 *
	 * @param userId
	 * @param user
	 * @param locale {@link String}
	 * @return User
	 * @throws EntityNotFoundException
	 */
	public UpdateUserRequest updateUser(Integer userId, UpdateUserRequest updateUserRequest) throws EntityNotFoundException {

		UserEntity updatedUser = userService.updateUser(userId, updateUserRequest);

		return updateUserRequest;
	}

//	/**
//	 * Update existing user in Database
//	 *
//	 * @param userId
//	 * @param user
//	 * @param locale {@link String}
//	 * @return User
//	 * @throws EntityNotFoundException
//	 */
//	public User updateUserImage(Integer userId, UpdateImageRequest updateUserRequest) throws EntityNotFoundException {
//
//		UserEntity userEntity = MapperUtil.convertModelToEntity(updateUserRequest, UserEntity.class);
//
//		UserEntity updatedUser = userService.updateUserInfo(userId, userEntity);
//
//		return MapperUtil.convertEntityToModel(updatedUser, User.class);
//	}

	/**
	 * Delete user in Database
	 *
	 * @param userId
	 * @return User
	 * @throws BadRequestException
	 * @throws EntityNotFoundException
	 */
	public void deleteUser(Integer userId) throws EntityNotFoundException {
		userService.deleteUser(userId);
	}

	/**
	 * Return List of Users present in the system.
	 *
	 * @return UserList
	 */
	public Page<User> getUsers(int pageNo, int pageSize, String sortBy, String sortDir, boolean pageableStatus) {
		if (!pageableStatus) {
			pageSize = Integer.MAX_VALUE;
		}

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<UserEntity> users =  userService.getUsers(pageable);

        List<User> companies = new ArrayList<>();
        companies.addAll(users.stream().map(entity -> MapperUtil.convertEntityToModel(entity, User.class))
				.collect(Collectors.toList()));
        return new PageImpl<>(companies, pageable, users.getTotalElements());
	}

//	/**
//	 * Check email already exists
//	 *
//	 * @param emailId String value
//	 * @return Boolean value
//	 */
//	public boolean isUserExists(String emailId) {
//
//		return userService.isUserExists(emailId);
//
//	}
//
//	/**
//	 * Update user password
//	 *
//	 * @param idexxUserToken
//	 * @param password
//	 * @param locale         {@link String}
//	 * @throws EntityNotFoundException
//	 * @throws UnauthorizedException
//	 * @throws BadRequestException
//	 */
//	public void updatePassword(Integer userId, String password) throws EntityNotFoundException, BadRequestException {
//		userService.updatePasword(userId, password);
//	}

	/**
	 * Get User by userId
	 *
	 * @param userId {@link Long}
	 * @return User {@link User}
	 * @throws EntityNotFoundException EntityNotFoundException
	 */
	public User getUserById(Integer userId) throws EntityNotFoundException {
		return MapperUtil.convertEntityToModel(userService.findById(userId), User.class);
	}


//	/**
//	 * Get User by userId
//	 *
//	 * @param userId {@link Long}
//	 * @return User {@link User}
//	 * @throws EntityNotFoundException EntityNotFoundException
//	 */
//	public UserPermissionResponse getUserModuleById(Integer userId) throws EntityNotFoundException {
//		UserPermissionResponse userPermissionResponse =  new UserPermissionResponse();
//		userPermissionResponse.setUser(MapperUtil.convertEntityToModel(userService.findById(userId), User.class));
//		userPermissionResponse.setUserModules(userService.getAllUserModules(userId));
//		return userPermissionResponse;
//	}
//	/**
//	 * SendEmail
//	 *
//	 * @param emailId {@link String}
//	 * @param locale  {@link String}
//	 * @throws EntityNotFoundException EntityNotFoundException
//	 * @throws BadRequestException     BadRequestException
//	 * @throws TemplateException
//	 * @throws MessagingException
//	 * @throws IOException
//	 */
//	public void sendEmail(String username) throws EntityNotFoundException, BadRequestException, IOException, MessagingException, TemplateException {
//
//		UserEntity user = userService.findByUsername(username);
//		/**
//		 * save token
//		 */
//		int randomPIN = (int)(Math.random()*9000)+1000;
//		user.setToken(randomPIN);
//		user.setPasswordExpairyTime(DateUtil.addDays(new Date(), 1));
//		mailService.sendEmailVerification(randomPIN,username,user.getFirstName()+" "+user.getLastName());
//		userService.updateUserInfo(user.getId(), user);
//	}
//
//	/**
//	 * Update user password
//	 *
//	 * @param idexxUserToken
//	 * @param password
//	 * @param locale         {@link String}
//	 * @throws EntityNotFoundException
//	 * @throws UnauthorizedException
//	 * @throws BadRequestException
//	 */
//	public User verifyOtp(int otp) throws EntityNotFoundException, BadRequestException {
//		UserEntity user = userService.findByToken(otp);
//
////		if(DateUtil.compaire(user.getPasswordExpairyTime()) {
//		if(true) {
////			user.setPassword(password);
////			userService.updateUser(user.getId(), user);
//		}else {
//			throw new BadRequestException(INVALID_OTP);
//		}
//		return MapperUtil.convertEntityToModel(user, User.class);
//
//	}
//
//	public UserDTO getcreateAuthenticationToken(String email,String password,String type) throws UsernameNotFoundException, UnauthorizedException, EntityNotFoundException {
//
//	UserEntity user = userService.findByUsername(email);
//	if(Objects.nonNull(user) && !user.getUserStatus().equals(UserStatus.ACTIVE)) {
//		throw new UnauthorizedException(ACC_NOT_ACT);
//	}
//	if(!type.equals(SOCIAL_TYPE)) {
//		authenticate(email, password);
//	}
//	final UserDetails userDetails = userService.loadUserByUsername(email);
//
//
//
//	UserDTO jwtResponse = new UserDTO();
//	jwtResponse.setEmail(user.getEmail());
//	jwtResponse.setId(user.getId());
//	jwtResponse.setFirstName(user.getFirstName());
//	jwtResponse.setLastName(user.getLastName());
//	jwtResponse.setContactNumber(user.getContactNumber());
//	jwtResponse.setImage(user.getImage());
//	jwtResponse.setJwttoken(jwtTokenUtil.generateToken(userDetails));
//	jwtResponse.setUserModules(userService.getAllUserModules(user.getId()));
//	jwtResponse.setUserStatus(user.getUserStatus());
//	if(Objects.nonNull(user.getRoles())){
//		jwtResponse.setRoles(user.getRoles().stream().map(role -> MapperUtil.convertEntityToModel(role, RoleRequest.class)).collect(Collectors.toSet()));
//	}
//
//	return jwtResponse;
//
//	}
//
//	private void authenticate(String username, String password) throws UnauthorizedException {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new UnauthorizedException(INVALID_CREADTIALS);
//		} catch (BadCredentialsException e) {
//			throw new UnauthorizedException(INVALID_CREADTIALS);
//		}
//	}
}
