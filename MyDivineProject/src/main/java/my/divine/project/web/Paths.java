package my.divine.project.web;

public class Paths {
    private Paths(){}

    //Common page
    public static final String ERROR_PAGE = "WEB-INF/view/jsp/common/error_page.jsp";
    public static final String MAIN_PAGE = "WEB-INF/view/jsp/common/main_page.jsp";
    public static final String COURSE_LIST_PAGE = "WEB-INF/view/jsp/registrated/common/course_list_page.jsp";
    public static final String AUTHENTICATION_PAGE = "/html/authentication.html";
    public static final String REGISTRATION_PAGE = "/html/registration.html";
    public static final String COURSE_LIST_SORT_PAGE = "WEB-INF/view/jsp/registrated/common/course_list_sort.jsp";
    public static final String PERSONAL_PAGE = "WEB-INF/view/jsp/registrated/common/personal_page.jsp";

    //admin
    public static final String CREATE_COURSE_PAGE = "WEB-INF/view/jsp/registrated/admin/create_course_page.jsp";
    public static final String EDIT_COURSES_LIST_PAGE = "WEB-INF/view/jsp/registrated/admin/edit_courses_list_page.jsp";
    public static final String EDIT_COURSES_PAGE = "WEB-INF/view/jsp/registrated/admin/edit_course_page.jsp";
    public static final String POPULAR_COURSES_PAGE = "WEB-INF/view/jsp/registrated/admin/popular_course_page.jsp";


    //teacher page
    public static final String GRADE_BOOK_PAGE = "WEB-INF/view/jsp/registrated/teacher/grade_book.jsp";
    public static final String VIEW_COURSE_PAGE = "WEB-INF/view/jsp/registrated/teacher/view_courses.jsp";

    //student page
    public static final String STUDENT_COURSES_PAGE = "WEB-INF/view/jsp/registrated/student/student_courses.jsp";


    //servlet
    public static final String  AUTHENTICATION_SERVLET = "aut";
    public static final String  REGISTRATION_SERVLET = "reg";
    public static final String CONTROLLER_SERVLET = "controller";
    public static final String MAIN_SERVLET = "main";
    public static final String ERROR_SERVLET = "error";
    public static final String COURSE_LIST_SERVLET = "list";
    public static final String PERSONAL_SERVLET = "personal";
    public static final String VIEW_COURSE_SERVLET = "view";
    public static final String STUDENT_COURSES_SERVLET = "stud";
    public static final String CREATE_COURSE_SERVLET = "create";
    public static final String EDIT_COURSE_SERVLET = "edit";
    public static final String REPORT_CARD_SERVLET = "report";

    // PDF reports
    public static final String PDF_REPORTS_PATH = "WEB-INF/resources/settings.properties";

}
