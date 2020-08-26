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

    public < E > Map loadCensusData( String csvFilePath, Class < E > censusCSVClass ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
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
                case "censusanalyser.IndianStateCodeCSV":
                    StreamSupport.stream( censusCSVIterable.spliterator(), false ).map( IndianStateCodeCSV.class :: cast )
                            .forEach( csvState -> censusDAOMap.put( csvState.stateName, new CensusDAO( csvState ) ) );
                    break;
            }
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
}
