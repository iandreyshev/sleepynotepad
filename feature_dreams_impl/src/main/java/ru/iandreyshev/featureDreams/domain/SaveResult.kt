package ru.iandreyshev.featureDreams.domain

enum class SaveResult {
    SUCCESS,
    ERROR_EMPTY_DREAM,
    ERROR_BLANK_DREAM,
    ERROR_LARGE_DREAM,
    ERROR_NO_CONNECTION,
    ERROR_UNDEFINED
}
