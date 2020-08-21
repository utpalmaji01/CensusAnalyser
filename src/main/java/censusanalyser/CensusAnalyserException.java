package censusanalyser;

public class CensusAnalyserException extends Exception {

    public enum ExceptionType {
        NO_DATA, CENSUS_FILE_PROBLEM, INVALID_TYPE_FILE, UNABLE_TO_PARSE
    }

    public ExceptionType type;

    public CensusAnalyserException( String message, ExceptionType type ) {
        super( message );
        this.type = type;
    }

    public CensusAnalyserException( String message, String name ) {
        super( message );
        this.type = ExceptionType.valueOf( name );
    }

    public CensusAnalyserException( String message, ExceptionType type, Throwable cause ) {
        super( message, cause );
        this.type = type;
    }
}
