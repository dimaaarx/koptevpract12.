public interface LogFilter {
    void filter(String sourceFile, String targetFile, LogLevel level);
}
