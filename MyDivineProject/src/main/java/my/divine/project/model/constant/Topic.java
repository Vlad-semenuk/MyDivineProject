package my.divine.project.model.constant;

public enum Topic {
    JAVA, DEVOPS, PHP, ANDROID, PYTHON, RUBY, JAVASCRIPT, AUTOMATED_TESTING;

    public static Topic getTopicById(int topicId) {
        return Topic.values()[--topicId];
    }

    public String getName() { return name().toLowerCase(); }

    public int getId() {
        return ordinal() + 1;
    }
}
