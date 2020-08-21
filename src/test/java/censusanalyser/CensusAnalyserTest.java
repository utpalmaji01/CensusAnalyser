package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    // path for indian census data
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CENSUS_CSV_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INVALID_CENSUS_HEADER_PATH = "./src/test/resources/InvalidHeaderIndiaStateCensusData.csv";
    private static final String INVALID_CENSUS_DELIMITER_PATH = "./src/test/resources/InvalidDelimiterIndiaStateCensusData.csv";

    //path for indian state code
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_STATE_CODE_CSV_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INVALID_STATE_CODE_HEADER_PATH = "./src/test/resources/InvalidHeaderIndiaStateCodeData.csv";
    private static final String INVALID_STATE_CODE_DELIMITER_PATH = "./src/test/resources/InvalidDelimiterIndiaStateCodeData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            Integer numOfRecords = censusAnalyser.loadIndiaCensusData( INDIA_CENSUS_CSV_FILE_PATH );
            Assert.assertEquals( ( Integer ) 29, numOfRecords );
        } catch ( CensusAnalyserException e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndiaCensusData( WRONG_CENSUS_CSV_FILE_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }

    @Test
    public void givenIndiaCensusData_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndiaCensusData( WRONG_CENSUS_CSV_TYPE_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.INVALID_TYPE_FILE, e.type );
        }
    }

    @Test
    public void givenIndiaCensusData_WhenInvalidHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndiaCensusData( INVALID_CENSUS_HEADER_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type );
        }
    }

    @Test
    public void givenIndiaCensusData_WhenInvalidDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndiaCensusData( INVALID_CENSUS_DELIMITER_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type );
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            Integer numOfRecords = censusAnalyser.loadIndianStateCodeData( INDIA_STATE_CODE_CSV_FILE_PATH );
            Assert.assertEquals( ( Integer ) 37, numOfRecords );
        } catch ( CensusAnalyserException e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndianStateCodeData( WRONG_STATE_CODE_CSV_FILE_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type );
        }
    }

    @Test
    public void givenIndiaStateCode_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndianStateCodeData( WRONG_STATE_CODE_CSV_TYPE_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.INVALID_TYPE_FILE, e.type );
        }
    }

    @Test
    public void givenIndiaStateCode_WhenInvalidHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndianStateCodeData( INVALID_STATE_CODE_HEADER_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type );
        }
    }

    @Test
    public void givenIndiaStateCode_WhenInvalidDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( CensusAnalyserException.class );
            censusAnalyser.loadIndianStateCodeData( INVALID_STATE_CODE_DELIMITER_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type );
        }
    }
}
