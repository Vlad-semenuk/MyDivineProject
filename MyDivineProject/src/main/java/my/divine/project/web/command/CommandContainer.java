package my.divine.project.web.command;

import my.divine.project.web.command.admin.*;
import my.divine.project.web.command.common.*;
import my.divine.project.web.command.student.*;
import my.divine.project.web.command.teacher.GradeBookCommand;
import my.divine.project.web.command.teacher.RemoveStudentCommand;
import my.divine.project.web.command.teacher.SetAssessmentCommand;
import my.divine.project.web.command.teacher.ViewCourseCommand;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    private static Map<String, Command> commands = new HashMap<>();

    static {
        // Authenticating command
        commands.put("aut", new AuthenticationCommand());
        commands.put("reg", new RegCommandCommand());
        commands.put("logout", new LogOutCommandCommand());

        //Common command
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("toMainPage", new ToMainPageCommand());
        commands.put("toCourseList", new ToCourseListCommand());
        commands.put("toCourseListSort", new CourseListSortCommand());
        commands.put("toCourseListTeacher", new CourseListTeacherCommand());
        commands.put("toCourseListTopic", new CourseListTopicCommand());
        commands.put("toGradeBook", new RegistrationToCourseCommand());
        commands.put("toUserCourses", new ToUserCoursesCommand());
        commands.put("toPersonalPage", new ToPersonalCommand());
        commands.put("toDeRegistration", new DeRegistrationCommand());
        commands.put("toViewCourse", new ViewCourseCommand());
        commands.put("toTeacherCourses", new GradeBookCommand());
        commands.put("setAssessment", new SetAssessmentCommand());
        commands.put("toExclude", new RemoveStudentCommand());
        commands.put("createCourse", new CreateCourseCommand());
        commands.put("toEditCourses", new ToEditCourseListCommand());
        commands.put("thisCourseEdit", new ToEditCourseCommand());
        commands.put("editCourse", new EditCourseCommand());
        commands.put("toDeleteCourse", new DeleteCourseCommand());
        commands.put("toAddTeacher", new AddTeacherCommand());
        commands.put("toBlock", new BlockUserCommand());
        commands.put("toUnblock", new UnblockUserCommand());
        commands.put("toEditUser", new EditUserCommand());
        commands.put("generateReport", new ReportCardGenerateCommand());
        commands.put("popular", new PopularCourseCommand());

    }

    public static Command getCommand(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        }
        LOG.trace(String.format("Command not found, name --> %s", commandName));
        return commands.get("noCommand");
    }


}
