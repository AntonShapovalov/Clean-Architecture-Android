package clean.architecture.domain.search.model

/**
 * Search text validation errors.
 */
enum class SearchValidationError {

    /**
     * The search text is empty.
     */
    EMPTY_TEXT,

    /**
     * The search text is too short.
     */
    TOO_SHORT_TEXT,

    /**
     * The search text is too long.
     */
    TOO_LONG_TEXT,

    /**
     * The search text contains invalid characters.
     */
    INVALID_CHARACTERS
}
