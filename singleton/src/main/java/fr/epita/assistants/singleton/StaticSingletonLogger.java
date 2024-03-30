package fr.epita.assistants.singleton;

import fr.epita.assistants.logger.Logger;

public class StaticSingletonLogger implements Logger {

    private static class InstanceHolder {
        private StaticSingletonLogger _INSTANCE = new StaticSingletonLogger();
    }

    protected static int info_counter = 0;
    protected static int warn_counter = 0;
    protected static int error_counter = 0;

    private StaticSingletonLogger() {
    }

    public static StaticSingletonLogger getInstance() {
        return new InstanceHolder()._INSTANCE;
    }

    @Override
    public void log(Logger.Level level, String message) {
        if (level == Level.INFO)
            info_counter++;
        else if (level == Level.WARN)
            warn_counter++;
        else
            error_counter++;

        System.err.println(Logger.getFormattedLog(level, message));

    }

    @Override
    public int getInfoCounter() {
        return info_counter;
    }

    @Override
    public int getWarnCounter() {
        return warn_counter;
    }

    @Override
    public int getErrorCounter() {
        return error_counter;
    }

    @Override
    public void reset() {
        info_counter = 0;
        warn_counter = 0;
        error_counter = 0;
    }
}
