package censusanalyser;

import com.google.gson.Gson;
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

    //path for us census data
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            Integer numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH );
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
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, WRONG_CENSUS_CSV_FILE_PATH );
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
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, WRONG_CENSUS_CSV_TYPE_PATH );
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
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA,INVALID_CENSUS_HEADER_PATH );
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
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INVALID_CENSUS_DELIMITER_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type );
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            Integer numOfRecords = censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CODE_CSV_FILE_PATH );
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
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, WRONG_STATE_CODE_CSV_FILE_PATH );
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
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH, WRONG_STATE_CODE_CSV_TYPE_PATH );
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
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INVALID_STATE_CODE_HEADER_PATH );
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
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INVALID_STATE_CODE_DELIMITER_PATH );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnWestBengalAtLast() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH );
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( stateWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( "West Bengal", indiaCensusCSV[ 28 ].stateName );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnAndhraPradeshAtFirst() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH );
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( stateWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( "Andhra Pradesh", indiaCensusCSV[ 0 ].stateName );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnLeastPopulatedAtFirst() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData( CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH );
            String populationWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( populationWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( 607688, indiaCensusCSV[ 0 ].population );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnMostPopulatedAtLast() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH );
            String populationWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( populationWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( 199812341, indiaCensusCSV[ 28 ].population );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnAreaInSqKm_ShouldReturnSmallestAreaAtFirst() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH );
            String areaInSqKmWiseSortedCensusData = censusAnalyser.getAreaInSqKmWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( areaInSqKmWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( 3702, indiaCensusCSV[ 0 ].areaInSqKm );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnAreaInSqKm_ShouldReturnLargestAreaAtLast() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH );
            String areaInSqKmWiseSortedCensusData = censusAnalyser.getAreaInSqKmWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( areaInSqKmWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( 342239, indiaCensusCSV[ 28 ].areaInSqKm );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnLargestDensityAtLast() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH );
            String densityPerSqKmWiseSortedCensusData = censusAnalyser.getDensityPerSqKmWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( densityPerSqKmWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( 1102, indiaCensusCSV[ 28 ].densityPerSqKm );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSmallestDensityAtFirst() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH );
            String densityPerSqKmWiseSortedCensusData = censusAnalyser.getDensityPerSqKmWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson( densityPerSqKmWiseSortedCensusData, IndiaCensusCSV[].class );
            Assert.assertEquals( 50, indiaCensusCSV[ 0 ].densityPerSqKm );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnADatFirst() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH );
            String stateWiseSortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndianStateCodeCSV[] indiaCensusCSV = new Gson().fromJson( stateWiseSortedCensusData, IndianStateCodeCSV[].class );
            Assert.assertEquals( "AD", indiaCensusCSV[ 0 ].stateCode );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnWBatLast() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH );
            String stateWiseSortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndianStateCodeCSV[] indiaCensusCSV = new Gson().fromJson( stateWiseSortedCensusData, IndianStateCodeCSV[].class );
            Assert.assertEquals( "WB", indiaCensusCSV[ 36 ].stateCode );
        } catch ( CensusAnalyserException e ) {
           Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            Integer numOfRecords = censusAnalyser.loadCensusData( CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH );
            Assert.assertEquals( ( Integer ) 51, numOfRecords );
        } catch ( CensusAnalyserException e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStateName_ShouldReturnWyomingAtLast() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData( CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH );
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            USCensusCSV[] usCensusCSV = new Gson().fromJson( stateWiseSortedCensusData, USCensusCSV[].class );
            Assert.assertEquals( "Wyoming", usCensusCSV[ 50 ].stateName );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStateName_ShouldReturnAlabamaAtFirst() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH );
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            USCensusCSV[] usCensusCSV = new Gson().fromJson( stateWiseSortedCensusData, USCensusCSV[].class );
            Assert.assertEquals( "Alabama", usCensusCSV[ 0 ].stateName );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnWyomingAtFirst() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH );
            String populationWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            USCensusCSV[] usCensusCSV = new Gson().fromJson( populationWiseSortedCensusData, USCensusCSV[].class );
            Assert.assertEquals( "Wyoming", usCensusCSV[ 0 ].stateName );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnCaliforniaAtLast() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH );
            String populationWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            USCensusCSV[] usCensusCSV = new Gson().fromJson( populationWiseSortedCensusData, USCensusCSV[].class );
            Assert.assertEquals( "California", usCensusCSV[ 50 ].stateName );
        } catch ( CensusAnalyserException e ) {
            Assert.assertEquals( CensusAnalyserException.ExceptionType.NO_DATA, e.type );
        }
    }
}
