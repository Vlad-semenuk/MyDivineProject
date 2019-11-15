package my.divine.project.exception;

public class ExceptionMessages {

    private ExceptionMessages() {
    }


    //DB
    public static final String ERRORS_WHEN_INITIALIZING_DS_MESSAGE = "Errors when initializing the DataSource";
    public static final String FAILED_TO_GET_CONNECTION_MESSAGE = "Failed to get connection";
    public static final String COULD_NOT_CLOSE_CONNECTION_MESSAGE = "Could not close connection";
    public static final String FAILED_TO_MAKE_CHANGES_MESSAGE = "Failed to make changes";
    public static final String COULD_NOT_ROLLBACK_TRANSACTION_MESSAGE = "Could not rollback transaction";
    public static final String TRANSACTION_ERROR_MESSAGE = "Transaction error";


    // User
    public static final String COULD_NOT_EDIT_USER_MESSAGE = "Could not edit user";
    public static final String COULD_NOT_SET_USER_FIELD_ROLE_MESSAGE= "Could not set user field role";
    public static final String COULD_NOT_SET_USER_FIELD_BLOCK_MESSAGE= "Could not set user field block";
    public static final String FAILED_TO_GET_USER_BY_LOGIN_AND_PASS_MESSAGE =
            "Failed to get user by login and password";
    public static final String USER_NOT_EXIST_MESSAGE = "User with such login and password does not exist";
    public static final String FAILED_TO_GET_USER_BY_LOGIN_MESSAGE = "Failed to get user by login";
    public static final String COULD_NOT_CREATE_USER_MESSAGE =
            "Could not create user, maybe this login or password already exists";
    public static final String FAILED_TO_GET_ALL_TEACHERS_MASSAGE = "Failed to get fll teachers";

    //Course
    public static final String COULD_NOT_CREATE_COURSE_MASSAGE = "Could not create course";
    public static final String FAILED_TO_GET_COURSES_MASSAGE = "Failed to get courses";
    public static final String FAILED_TO_GET_COURSES_BY_TOPIC_MASSAGE = "Failed to get courses by Topic";
    public static final String FAILED_TO_GET_COURSES_BY_STATE_MASSAGE = "Failed to get courses by state";
    public static final String FAILED_TO_GET_COURSES_BY_TEACHER_MASSAGE = "Failed to get courses by teacher";
    public static final String FAILED_TO_GET_COURSES_BY_SORT_MASSAGE = "Failed to get courses by sort name ";
    public static final String FAILED_TO_GET_COURSES_BY_COUNT_MASSAGE = "Failed to get courses by sort count ";
    public static final String COULD_NOT_SET_COURSE_FIELD_TEACHER_MASSAGE = "Could not set course field teacher";
    public static final String COULD_NOT_SET_COURSE_FIELD_STATE_MASSAGE = "Could not set course field state";
    public static final String COULD_NOT_UPDATE_COURSE_MASSAGE = "Could not update course";
    public static final String COULD_NOT_SET_COUNT_MASSAGE = "Could not set count to course";
    public static final String COULD_NOT_DELETE_COURSE_MASSAGE = "Could not delete course";
    public static final String FAILED_TO_GET_COURSE_BY_ID_MESSAGE = "Failed to get course by ID ";



    //Grade book
    public static final String COULD_NOT_SET_USER_TO_COURSE_MASSAGE = "Could not set user to course";
    public static final String FAILED_TO_GET_ALL_USERS_FROM_COURSE_MASSAGE = "Failed to get all users from course";
    public static final String FAILED_TO_GET_ALL_COURSE_IN_USER_MASSAGE = "Failed to get all courses in user";
    public static final String FAILED_TO_GET_COUNT_STUDENTS_FROM_COURSE_MASSAGE = "Failed to get count students to course";
    public static final String COULD_NOT_DELETE_USER_FROM_COURSE_MASSAGE = "Could not delete user from course";
    public static final String COULD_NOT_SET_ASSESSMENT_TO_STUDENT_BY_COURSE_MASSAGE = "Could not set assessment to student by course";
    public static final String USER_IS_ALREADY_REGISTERED_FOR_COURSE_MASSAGE = "User is already registered for the course";


    //common
    public static final String INCORRECT_SEARCH_PARAMETER_MESSAGE = "Incorrect search parameter";
    public static final String COULD_NOT_GENERATE_REPORT_CARD_MESSAGE = "Could not generate report card";




}
