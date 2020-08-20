package censusanalyser;

public class CensusAnalyserException extends Exception {


    enum ExceptionType {
        CENSUS_FILE_PROBLEM, NOT_CSV_TYPE_OR_INVALID_HEADER_OR_DELIMITER
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
}
