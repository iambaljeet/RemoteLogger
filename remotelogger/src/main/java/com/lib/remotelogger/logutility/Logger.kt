package com.lib.remotelogger.logutility


object Logger {
    private val STACK_TRACE_LEVELS_UP: Int = 5

    /**
     * Get the current line number. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @author kvarela
     * @return int - Current line number.
     */
    val lineNumber: Int
        get() = Thread.currentThread().stackTrace[STACK_TRACE_LEVELS_UP]
            .lineNumber
    // Removing ".java" and returning class name

    /**
     * Get the current class name. Note, this will only work as called from this
     * class as it has to go a predetermined number of steps up the stack trace.
     * In this case 5.
     *
     * @author kvarela
     * @return String - Current line number.
     */
    val className: String
        get() {
            val fileName: String =
                Thread.currentThread().stackTrace[STACK_TRACE_LEVELS_UP]
                    .fileName

            // Removing ".java" and returning class name
            return fileName.substring(0, fileName.length - 5)
        }

    /**
     * Get the current method name. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @author kvarela
     * @return String - Current line number.
     */
    val methodName: String
        get() {
            return Thread.currentThread().stackTrace[STACK_TRACE_LEVELS_UP]
                .methodName
        }

    /**
     * Returns the class name, method name, and line number from the currently
     * executing log call in the form .()-
     *
     * @author kvarela
     * @return String - String representing class name, method name, and line
     * number.
     */
    val classNameMethodNameAndLineNumber: String
        get() {
            return "[$className.$methodName()-$lineNumber]: "
        }
}