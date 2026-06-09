public enum LogLevel {
    CRITICAL("[CRITICAL]"),
    ERROR("[ERROR]"),
    WARNING("[WARNING]"),
    INFO("[INFO]"),
    DEBUG("[DEBUG]");

    private final String keyword;

    LogLevel(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
