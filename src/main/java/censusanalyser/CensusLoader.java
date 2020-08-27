package censusanalyser;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public Map < String, CensusDAO > censusDAOMap;

    public CensusLoader() {
        this.censusDAOMap = new HashMap <>();
    }

    public Map<String, CensusDAO> loadCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return this.loadCensusData(IndiaCensusCSV.class, csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return this.loadCensusData(USCensusCSV.class, csvFilePath);
        } else {
            throw new CensusAnalyserException("Incorrect", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
        }
    }



    public < E > Map loadCensusData(  Class < E > censusCSVClass,String... csvFilePath) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath[0] ) ) ) {
            if ( ! csvFilePath[0].contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < E > csvFileIterator = csvBuilder.getCSVFileIterator( reader, censusCSVClass );
            Iterable < E > censusCSVIterable = () -> csvFileIterator;
            String className = censusCSVClass.getName();
            switch ( className ) {
                case "censusanalyser.IndiaCensusCSV":
                    StreamSupport.stream( censusCSVIterable.spliterator(), false ).map( IndiaCensusCSV.class :: cast )
                            .forEach( csvState -> censusDAOMap.put( csvState.stateName, new CensusDAO( csvState ) ) );
                    break;
                case "censusanalyser.USCensusCSV":
                    StreamSupport.stream( censusCSVIterable.spliterator(), false ).map( USCensusCSV.class :: cast )
                            .forEach( csvState -> censusDAOMap.put( csvState.stateName, new CensusDAO( csvState ) ) );
                    break;
                default:
                    throw new CensusAnalyserException("Incorrect", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
            }
            if (csvFilePath.length == 1)
                return censusDAOMap;
            this.loadIndianStateCodeData(csvFilePath[1]);
            return censusDAOMap;
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        } catch ( IllegalArgumentException e ) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.INVALID_TYPE_FILE,
                    e.getCause() );
        } catch ( RuntimeException e ) {
            throw new CensusAnalyserException( e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE,
                    e.getCause() );
        } catch ( CSVBuilderException e ) {
            throw new CensusAnalyserException( e.getMessage(), e.type.name() );
        }
    }

    public Map loadIndianStateCodeData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < IndianStateCodeCSV > csvFileIterator = csvBuilder.getCSVFileIterator( reader,
                    IndianStateCodeCSV.class );
            Iterable < IndianStateCodeCSV > censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream( censusCSVIterable.spliterator(), false ).forEach( stateCodeCSV ->
                    censusDAOMap.put( stateCodeCSV.stateName, new CensusDAO( stateCodeCSV ) ) );
            return censusDAOMap;
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        } catch ( IllegalArgumentException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_TYPE_FILE,
                    e.getCause() );
        } catch ( RuntimeException e ){
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE,
                    e.getCause() );
        } catch ( CSVBuilderException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    e.type.name() );
        }
    }
}
