package com.houstondirectauto.refurb.util;

/**
 * App Constants
 * */
public class Constants {
	private Constants() {
		throw new AssertionError();
	}
	
	/**
	 * Welcome meaasge
	 */
	public static final String WELCOME = "Welcome to Transport Houston Direct Refurb Backend App";

	public static final String REFURB = "for Refurb System ";

	
	/**
	 * Swagger Constants
	 */
	public static final String APP_TITLE = "Transport Houston Direct Refurb Backend App";
	public static final String APP_DESCRIPTION = "Transport Houston Direct Refurb Backend App.";
	public static final String APP_TERM_URL = "******";
	public static final String APP_CONTACT_NAME = "******";
	public static final String APP_CONTACT_URL = "******";
	public static final String APP_CONTACT_EMAIL = "******";
	public static final String APP_LICENCESE = "******";
	public static final String APP_LICENCESE_URL = "******";
	public static final String APP_VERSION = "v1.0.0";
	
	/**
	 * Swagger constants
	 *
	 */
	public static final String NO_CONTENT_MESSAGE = "No Content in case there is no technical or validation errors";
	public static final String SUCCESS_MESSAGE = "OK in case there is no technical or validation errors";
	public static final String BAD_REQUESTS_MESSAGE = "Bad Requests";
	public static final String AUTHORISED_USER_MESSAGE = "Un Authorised user see error object for details";
	public static final String NOT_FOUND_MESSAGE = "Not Found";
	public static final String FORBIDDEN_USER_MESSAGE = "Forbidden user see error object for details";
	public static final String ERROR_MESSAGE = "Something went wrong on the Server Side";
	public static final String BEARER_AUTH = "Authorization";

	public static final String STATUS_CODE_OK = "200";
	public static final String STATUS_CODE_CREATED = "201";
	public static final String STATUS_CODE_NO_CONTENT = "204";
	public static final String STATUS_CODE_BAD_REQUEST = "400";
	public static final String SHOP_TAGS = "store";

	public static final String STATUS_AUTHORISED = "401";
	public static final String STATUS_NOT_FOUND = "404";
	public static final int STATUS_FORBIDDEN = 403;
	public static final String STATUS_CODE_INTERNAL_ERROR = "500";//this one is for string
	public static final int STATUS_ERROR = 500;

	public static final String JWT_BEARER_AUTH = "jwtBearerAuth";
	public static final String JWT_BEARER_FORMAT = "JWT";
	public static final String JWT_BEARER_SCHEME = "bearer";
	public static final String BASE_URL = "https://s3.us-east-2.amazonaws.com/transportsite/";
	
	
	public static final String DEFAULT_PAGE_NUMBER = "0";
    public  static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "desc";
    public static final boolean DEFAULT_PAGEABLE = false;
    public static final String DEFAULT_STORE_STATUS = "ACTIVE";
    
    public static final String PAGE_NUMBER_KEY = "pageNo";
    public  static final String PAGE_SIZE_KEY = "pageSize";
    public static final String SORT_BY_KEY = "sortBy";
    public static final String SORT_DIRECTION_KEY = "sortDir";
    public static final String PAGEABLE = "pageable";
	
	//Two_factor_Auth
	public static final String DESCRIPTION_2FA_SENT = "2FA code sent successfully.";
	public static final String DESCRIPTION_2FA_VERIFIED = "2FA code verification successful.";
	public static final String DESCRIPTION_BAD_REQUEST = "Bad request, invalid user ID.";
	public static final String DESCRIPTION_SERVER_ERROR = "Internal server error.";
	public static final String SUMMARY_2FA_REQUEST="Requested 2FA Code";
	public static final String DESCRIPTION_2FA_REQUEST="Generates and sends a 2FA code to the user.";
	public static final String SUMMARY_2FA_VERIFY="Verify 2FA Code";
	public static final String CODE_REQUIRED = "2FA code is required";
	public static  final String DESCRIPTION_2FA_VERIFY="Verifies the provided 2FA code for the user.";
	public static final String DESCRIPTION_2FA_MISMATCH = "2FA code mismatch.";
	public static final String DESCRIPTION_2FA_VER_SERVER_ERROR = "An error occurred while verifying the 2FA code.";
	public static final String DESCRIPTION_2FA_REQ_SERVER_ERROR = "An error occurred while processing the 2FA request.";


	/**
	 * Auto End Point Urls
	 */
	public static final String AUTO = "/auto";
	public static final String AUTO_TAGS = "auto";
	public static final String OK_STATUS = "++Status OK++";
	public static final String STATUS_STRING_200 = "200";
	public static final int STATUS_INT_200 = 200;
	public static final String ACTIVE_INVENTORY_STATUS = "AVAILABLE";
	public static final String SOLD_INVENTORY_STATUS = "SOLD";
	public static final String INVENTORY_STATUS = "idmsInventoryStatus";
	public static final String REFURB_STATUS = "refurbStatus";
	public static final String SOURCE_STATUS = "source";
	public static final String DEFAULT_REFURB_STATUS = "NULL";
	
	public static final int NO_DATA_SYANC = 20;
	
	
	/**
	 * SCHEDULAR End Point Urls
	 */
	public static final String SCHEDULAR = "/cron";
	public static final String SCHEDULAR_IDMS = "/api/getIdmsData";
	public static final String SCHEDULAR_HDA = "/api/getHdaData";
	public static final String SCHEDULAR_REFURB = "/api/getRefurbData";
	public static final String SCHEDULAR_SMARTSHEET = "/api/getSmartsheet";
	public static final String SCHEDULAR_HDA_VEH = "/api/getHdaVehicalLocation";
	public static final String SCHEDULAR_IGCDMS_VEH = "/api/getIgcdmsVehical";
	public static final String RULE_API = "/api/run-rules";
	public static final String RUN_CRON = "/runCron";
	public static final String CRON_TAGS = "cron";
	/**
	 * SCHEDULAR Message
	 */
	public static final String SUCCESS = "Data fetched successfully";
	public static final String USER_NOT_EXIST = "Username doesn't exist in the system";
	public static final String USERS_NOT_EXIST = "User doesn't exist in the system";
	public static final String DELETE_SUCCESS = "Data deleted successfully";
	
	
	/**
	 * User End Point Urls
	 */
	public static final String USERS = "/user";
	public static final String AUTH = "/account";
	public static final String FORGOT_PASSWORD_REQUEST= "/forgot-password/request";
	public static final String FORGOT_PASSWORD_VERIFY= "/forgot-password/verify";
	public static final String USER_SIGNIN = "/login";
	public static final String USER_SOCIAL_SIGNIN = "/gmail/login";
	public static final String USER_ID = "/{userId}";
	public static final String USERS_ID = "userId";
	public static final String UPDATE_CREDENTIALS = "/account/password";
	public static final String VERIFY_USERNAME = "/verify/email";
	public static final String USERNAME = "username";
	public static final String VERIFY_OTP = "/verify/otp";
	public static final String USER_TAGS = "4. user";

	public static final String AUTH_TAGS = "2. auth";
	public static final String SOCIAL_TYPE = "SOCIAL";
	public static final String NORMAL_TYPE = "NORMAL";
	public static final String ACC_NOT_ACT = "User account does not activated, Please contact to administrator";
	
	public static final String VALIDATE = "/validate";
	public static final String EMAIL_ID = "emailId";
	public static final String FIND_USER_BY_EMAIL = VALIDATE + "/{" + EMAIL_ID + "}";
	
	
	
	/**
	 * Shop End Point Urls
	 */
	public static final String SHOPS = "/store";
	public static final String SHOP_ID = "/{storeId}";
	public static final String IMAGE = "/image";
	public static final String SHOPS_ID = "storeId";
	public static final String PROPOSE_LOCATION_ID = "proposeLocationId";
	public static final String PROPOSE_STATUS = "proposeStatus";
	public static final String STORE_STATUS = "storeStatus";
	public static final String REMOVE_IMAGE = IMAGE+"/{storeId}";
	
	
	/**
	 * Rule End Point Urls
	 */
	public static final String RULE = "/rule";
	public static final String COLUMN = "/column";
	public static final String RULE_ID = "/{ruleId}";
	public static final String RULES_ID = "ruleId";
	public static final String RULE_TAGS = "rule";
	public static final String RULE_NOT_EXIST ="Rule does't exist in the system";
	public static final String AUDIT_NOT_EXIST ="Audit does't exist in the system";
	

	/**
	 * Rule End Point Urls
	 */
	public static final String MOUDULE = "/module";
	public static final String MOUDULE_ID = "/{moduleId}";
	public static final String MOUDULES_ID = "moduleId";
	public static final String MOUDULE_TAGS = "module";
	
	/**
	 * Rule End Point Urls
	 */
	public static final String ROLE = "/role";
	public static final String ROLE_ID = "/{roleId}";
	public static final String ROLES_ID = "roleId";
	public static final String ROLE_TAGS = "3. role";
	

    
    
    
    /**
	 * Shop End Point Urls
	 */
	public static final String MANAGE_CRON = "/manage-cron";
	public static final String MANAGE_CRON_ID = "/{cronId}";
	public static final String MANAGE_CRONS_ID = "cronId";
	public static final String MANAGE_CRON_TAGS = "manage-cron";
	public static final String ID_NOT_EXIST = "id is not exist in the system";
	
	
	/**
	 * Media End Point Urls
	 */
	public static final String FILES = "/file";
	public static final String UPLOAD = "/upload";
	public static final String DOWNLOAD = "/download";
	public static final String DELETE_FILE = "/delete";
//	public static final String DOWNLOAD = "/{key}";
	public static final String FILE_TAGS = "file-upload";

	/**
	 * Vendor End Point Urls
	 */
	public static final String VENDOR = "/vendor";
	public static final String VENDOR_BY_STORES = "/vendor/{storeId}";
	public static final String VENDOR_ID = "/{vendorId}";
	public static final String VENDORS_ID = "vendorId";
	public static final String VENDOR_TAGS = "5. vendor";

	// ERR_MSG
	public static final String VENDOR_NOT_EXIST = "Vendor doesn't exist in the system";

	/**
	 * Department End Point Urls
	 */
	public static final String DEPARTMENT = "/department";
	public static final String DEPARTMENT_ID = "/{departmentId}";
	public static final String DEPARTMENTS_ID = "departmentId";
	public static final String DEPARTMENT_TAGS = "6. department";

	// ERR_MSG
	public static final String DEPARTMENT_NOT_EXIST = "Department doesn't exist in the system";
	/**
	 * Shop End Point Urls
	 */
	public static final String INVENTORY = "/inventory";
	public static final String INVENTORY_BY_STORES = "/store/{storeId}";
	public static final String INVENTORY_ID = "/{inventoryId}";
	public static final String INVENTORYS_ID = "inventoryId";
	public static final String INVENTORY_TAGS = "inventory";
	
	
	/**
	 * Cron Log Point Urls
	 */
	public static final String CRON_LOG = "/cron-audit-log";
	public static final String CRON_LOG_ID = "/{auditId}";
	public static final String CRON_LOGS_ID = "auditId";
	public static final String CRON_LOG_TAGS = "cron-audit-log";
	
	 /**
	 * Error messages 
	 */
	public static final String EMAIL_ALREADY_EXIST = "Email already exist";
	public static final String INVALID_OTP = "Invalid Otp";
	public static final String INVALID_CREADTIALS = "Invalid username/password";
	public static final String URL_IS_MANDATORY = "Url is mandatory";
	public static final String INSERT_SUCCESS = "Insert successfully";
	public static final String UPDATE_SUCCESS = "Update successfully";
	public static final String INVALID_FILE_IMAGES_EXTENSION = "Invalid file type only allow jpeg|png|jpg|svg!";
	public static final String MAX_IMAGES_SIZE = "File too Big, please select a file less than 5MBb";
	public static final String EMPTY_STRING = " ";
	public static final String RULE_IS_MANDATORY ="Rule is mandatory";
	public static final String EMAIL_IS_MANDATORY ="Email is mandatory";
	public static final String PASSWORD_IS_MANDATORY ="Password is mandatory";
	public static final String NAME_IS_MANDATORY ="Name is mandatory";
	public static final String LATITUDE_IS_MANDATORY ="Latitude is mandatory";
	public static final String LONGITUDE_IS_MANDATORY ="Longitude is mandatory";
	public static final String CONTACT_IS_MANDATORY ="Phone number is mandatory";
	public static final String PARKING_CAPACITY_IS_MANDATORY ="Parking capacity is mandatory";
	public static final String NICKNAMES_ARE_MANDATORY ="Nicknames are mandatory";
	
	/**
	 * Email Constants
	 */
	public static final String USER_NAME = "userName";
	public static final String NAME = "name";
	public static final String USER_EMAIL_TOKEN_VERIFICATION_LINK = "userEmailTokenVerificationLink";
	public static final String CONFIRM_EMAIL  = "confirmEmail";
	public static final String EMAIL_ACTIVATION  = "emailActivation";
	public static final String EMAIL_CONTENT  = "emailContent";
	public static final String EMAIL_VERIFICATION  = "emailVerification";
	public static final String EMAIL_HEADING  = "emailHeading";
	public static final String EMAIL_REQUIRED = "Email is required";
	public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
	public static final String TEAM  = "team";
	public static final String NOTE  = "note";
	public static final String OUR_BEST = "ourBest";
		
	/**
	 * Email Constants
	 */
	public static final String EMAIL_SUBJECT  = "Reset Transport Houston Direct Auto Password OTP";
	public static final String EMAIL_NOTE  = "If you didn't requested for a password reset you can ignore this email.<br/> Your password will not be changed.";
	public static final String EMAIL_TEAM  = "Transport Houston Direct Auto";
	public static final String EMAIL_OUR_BEST  = "Thanks";
	public static final String AUTH_TOKEN  = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcyMDE2MjU4NCwiaWF0IjoxNzIwMTYyNTg0fQ.n8WXqsPUXw1PhKkTNqiMv2wFy0s-VoDCEbZJe9DD4LQ";


	// Forgot Password API Descriptions
	public static final String FORGOT_PASSWORD_REQUEST_SUMMARY = "Forgot Password Request";
	public static final String FORGOT_PASSWORD_REQUEST_DESCRIPTION = "Send reset instructions to the user's email and phone.";
	public static final String FORGOT_PASSWORD_VERIFY_SUMMARY = "Verify Forgot Password Code";
	public static final String FORGOT_PASSWORD_VERIFY_DESCRIPTION = "Verify the 2FA code to reset the password.";
	//Exception
	public static final String VERIFICATION_SUCCESS = "Verification successful. You may now reset your password.";
	public static final String INVALID_2FA_CODE = "Invalid 2FA code.";
	public static final String USER_NOT_FOUND_BY_EMAIL_AND_PHONE = "User with provided email and phone not found";
	public static final String RESET_INSTRUCTIONS_SENT = "Reset instructions have been sent to your email and phone.";
	public static final String PHONE_REQUIRED = "Phone number is required";
	public static final String INVALID_PHONE_FORMAT = "Invalid phone number format";
}
