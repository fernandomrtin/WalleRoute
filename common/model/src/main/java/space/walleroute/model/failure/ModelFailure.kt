package space.walleroute.model.failure

private const val DEFAULT_FAILURE_STRING_RESOURCE = "empty failure message"

sealed class Failure(var msg: String = DEFAULT_FAILURE_STRING_RESOURCE) {
    data object NoData : Failure(msg = ErrorMessage.ERROR_NO_DATA)
    data object JsonParsingError : Failure(msg = ErrorMessage.ERROR_PARSING_FILE)
    data object Unknown : Failure(msg = ErrorMessage.ERROR_UNKNOWN)
}

object ErrorMessage {
    const val ERROR_NO_DATA = "No Data"
    const val ERROR_PARSING_FILE = "Error parsing file"
    const val ERROR_UNKNOWN = "Unknown Error"
}