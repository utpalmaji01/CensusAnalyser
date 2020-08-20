package censusanalyser;

public class CSVBuilderException extends Exception {

    enum ExceptionType {
        UNABLE_TO_PARSE, NOT_CSV_TYPE_OR_INVALID_HEADER_OR_DELIMITER
    }

    public ExceptionType type;

    public CSVBuilderException( String message, ExceptionType type ) {
        super( message );
        this.type = type;
    }
}
