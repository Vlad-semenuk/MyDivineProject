package my.divine.project.web.listener;

import my.divine.project.model.utility.ScheduledSetState;
import my.divine.project.web.command.commandUtils.CommandUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;

/**
 * Run scheduled Task
 */

@WebListener("/*")
public class ContextListener implements ServletContextListener {
    private static final long UPDATE_PERIOD = 60000;
    private ScheduledSetState scheduled;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("Context init started");

        ServletContext servletContext = servletContextEvent.getServletContext();
        Timer timer = new Timer();
        scheduled =  new ScheduledSetState();
        timer.schedule(scheduled, 100,UPDATE_PERIOD);



        log("Context init finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Context destroy started");

        scheduled.cancel();


        log("Context destroy finished");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }


}
