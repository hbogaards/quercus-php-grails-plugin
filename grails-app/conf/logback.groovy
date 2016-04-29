import static ch.qos.logback.classic.Level.*

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

scan()

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('CONSOLE', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{ISO8601}|%-5p|%-20.20t|%-32.32c{32}| %X%m%n"
    }
    filter(ThresholdFilter) {
        level = INFO
    }
}

appender('ROLLING_FILE', RollingFileAppender) {
    file = 'logs/quercus-php.log'
    encoder(PatternLayoutEncoder) {
        pattern = "%d{ISO8601}|%-5p|%-20.20t|%-32.32c{32}| %X%m%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = 'logs/quercus-php.log.%d{yyyy-MM-dd}.gz'
        maxHistory = 14
    }
}

root(DEBUG, ['ROLLING_FILE','CONSOLE'])

appender("FULL_STACKTRACE", FileAppender) {
    file = "logs/stacktrace.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}
logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
