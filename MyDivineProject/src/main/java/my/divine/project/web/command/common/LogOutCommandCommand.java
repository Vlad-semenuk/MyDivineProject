package my.divine.project.web.command.common;

import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Destroy session and return path to authentication page
 */
public class LogOutCommandCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LogOutCommandCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("Command starts");

        req.getSession().invalidate();

        LOG.debug("Command finished");
        return Paths.AUTHENTICATION_PAGE;
    }
}
