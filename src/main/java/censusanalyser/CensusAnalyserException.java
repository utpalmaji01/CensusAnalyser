package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, NOT_CSV_TYPE_OR_INVALID_HEADER
    }

    ExceptionType type;

    public CensusAnalyserException( String message, ExceptionType type ) {
        super( message );
        this.type = type;
    }

    public CensusAnalyserException( String message, ExceptionType type, Throwable cause ) {
        super( message, cause );
        this.type = type;
    }
}
