package my.divine.project.model.utility;

import my.divine.project.db.Facade;
import my.divine.project.db.TransactionManagerFacade;
import my.divine.project.db.connector.postgresql.PostgresService;
import my.divine.project.exception.AppException;
import my.divine.project.exception.ExceptionMessages;
import my.divine.project.exception.db.DBException;
import my.divine.project.model.constant.State;
import my.divine.project.model.entity.Course;

import org.apache.log4j.Logger;


import java.util.*;


/**
 * this class checks every 2 minutes the
 * course dates and assigns state.
 */

public class ScheduledSetState extends TimerTask {

    private static final Logger LOG = Logger.getLogger(ScheduledSetState.class);
    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresService.getInstance());

    private State state;

    private List<Course> courseList;



    @Override
    public void run() {
        LOG.trace("Scheduled run");

        try {
            courseList = FACADE.getCourses();
        } catch (AppException e){
            LOG.error(ExceptionMessages.FAILED_TO_GET_COURSES_MASSAGE, e);
        }


        for (int i = 0; i < courseList.size(); i++){

            Course course = courseList.get(i);

             state = checkDate(course);

             if (course.getState() != state){  //
                 course.setState(state);
                 try {
                     FACADE.setFieldState(course.getId(), state);
                 } catch (AppException e){
                     LOG.error(ExceptionMessages.COULD_NOT_SET_COURSE_FIELD_STATE_MASSAGE, e);
                 }

             }

        }
        LOG.trace("Scheduled finished");

    }

    /**
     * Algorithm:
     * dateStart > currentDate return: Open
     * dateStart < currentDate < dateEnd return: During
     * dateEnd < currentDate return: Finished
     * @param course
     * @return State
     */

    private State checkDate (Course course){
        int stateID = 1;
       Date date = new Date();

        if (course.getStartDate().getTime() > date.getTime()){
            return State.getStateById(stateID);
        } else if (course.getStartDate().getTime() < date.getTime() && course.getEndDate().getTime() > date.getTime()){
            stateID = 2;
        } else if (course.getEndDate().getTime() < date.getTime()){
            stateID = 3;
        }

        return State.getStateById(stateID);
    }
}
