package sh.lumin.loom.debug

import sh.lumin.loom.LoomUI
import java.util.logging.Logger
import kotlin.system.measureTimeMillis

/**
 * An object providing debug logging functionality for the LoomUI.
 */
object DebugLogger {
    private val logger: Logger = Logger.getLogger("LoomUI/Debug")

    /**
     * Logs timing information for a specific task.
     *
     * @param icon The debug icon associated with the log message.
     * @param message The message to log.
     * @param block The block of code to measure execution time for.
     */
    fun logTiming(icon: DebugIcon, message: String, block: () -> Unit) {
        val time = measureTimeMillis { block() }
        if(LoomUI.debugMode) {
            logger.info("${icon.icon} $message ${time}ms!")
        }
    }

    /**
     * Logs a debug message.
     *
     * @param icon The debug icon associated with the log message.
     * @param message The message to log.
     */
    fun log(icon: DebugIcon, message: String) {
        if(LoomUI.debugMode) {
            logger.info("${icon.icon} $message")
        }
    }
}