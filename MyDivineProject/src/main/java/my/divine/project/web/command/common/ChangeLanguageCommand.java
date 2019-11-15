package my.divine.project.web.command.common;

import my.divine.project.exception.AppException;
import my.divine.project.web.Paths;
import my.divine.project.web.command.Command;
import my.divine.project.web.validator.Validator;
import my.divine.project.web.validator.common.LanguageValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);

    private static final Validator<String> LANGUAGE_VALIDATOR = LanguageValidator.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String language = req.getParameter("language");
        LOG.trace(String.format("Request parameter: language --> %s", language));
        LANGUAGE_VALIDATOR.validate(language);
        req.getSession().setAttribute("language", language);

        LOG.debug("Command finished");
        return Paths.MAIN_PAGE;
    }
}
