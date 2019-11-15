package my.divine.project.model.constant;

import java.io.Serializable;

public enum Role implements Serializable {

    STUDENT, TEACHER, ADMIN;

    public static Role getRoleById(int roleId) {
        if (roleId <= 0){
            return null;
        }
        return Role.values()[--roleId];
    }

    public String getName() { return name().toLowerCase(); }

    public int getId() {
        return ordinal() + 1;
    }
}

