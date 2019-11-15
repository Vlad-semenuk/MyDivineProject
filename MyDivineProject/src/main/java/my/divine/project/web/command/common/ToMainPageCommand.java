package my.divine.project.web.command.common;

import my.divine.project.exception.AppException;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToMainPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToMainPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Paths.MAIN_PAGE;
    }
}
