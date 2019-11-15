package my.divine.project.model.constant;

import java.io.Serializable;

public enum  State implements Serializable {

     OPEN, DURING, FINISHED;

    public static State getStateById(int roleId) {
        return State.values()[--roleId];
    }

    public String getName() { return name().toLowerCase(); }

    public int getId() {
        return ordinal() + 1;
    }

}
