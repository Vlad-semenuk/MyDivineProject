package my.divine.project.web.command;

import my.divine.project.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
