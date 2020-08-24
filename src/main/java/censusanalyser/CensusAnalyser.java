package censusanalyser;

import com.google.gson.Gson;
import csvbuilder.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List < CensusDAO > censusDAOList;
    Map < String, CensusDAO > censusDAOMap;

    public CensusAnalyser() {
        this.censusDAOList = new ArrayList < CensusDAO >();
        this.censusDAOMap = new HashMap < String, CensusDAO >();
    }

    // load Indian Census data and analyse
    public Integer loadIndiaCensusData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < IndiaCensusCSV > csvFileIterator = csvBuilder.getCSVFileIterator( reader, IndiaCensusCSV.class );
            Iterable < IndiaCensusCSV > censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream( censusCSVIterable.spliterator(), false ).forEach( censusCSV ->
                                                                                    censusDAOMap.put( censusCSV.stateName,
                                                                                    new CensusDAO( censusCSV ) ) );
            return censusDAOMap.size();
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

    // load Indian State Code data and analyse
    public Integer loadIndianStateCodeData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < IndianStateCodeCSV > csvFileIterator = csvBuilder.getCSVFileIterator( reader, IndianStateCodeCSV.class );
            Iterable < IndianStateCodeCSV > censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream( censusCSVIterable.spliterator(), false ).forEach( stateCodeCSV ->
                                                                                censusDAOMap.put( stateCodeCSV.stateName,
                                                                                new CensusDAO( stateCodeCSV ) ) );
            return censusDAOMap.size();
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

    // load Indian Census data and analyse
    public Integer loadUSCensusData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < USCensusCSV > csvFileIterator = csvBuilder.getCSVFileIterator( reader, USCensusCSV.class );
            Iterable < USCensusCSV > censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream( censusCSVIterable.spliterator(), false ).forEach( censusCSV ->
                                                                                    censusDAOMap.put( censusCSV.stateName,
                                                                                    new CensusDAO( censusCSV ) ) );
            return censusDAOMap.size();
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

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        censusDAOList = censusDAOMap.values().stream().collect( Collectors.toList() );
        Comparator < CensusDAO > censusComparator = Comparator.comparing( CensusDAO :: getStateName );
        this.sort( censusDAOList, censusComparator );
        return new Gson().toJson( censusDAOList );
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        censusDAOList = censusDAOMap.values().stream().collect( Collectors.toList() );
        Comparator < CensusDAO > censusComparator = Comparator.comparing( CensusDAO :: getPopulation );
        this.sort( censusDAOList, censusComparator );
        return new Gson().toJson( censusDAOList );
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        censusDAOList = censusDAOMap.values().stream().collect( Collectors.toList() );
        Comparator < CensusDAO > censusComparator = Comparator.comparing( CensusDAO :: getStateCode );
        this.sort( censusDAOList, censusComparator );
        return new Gson().toJson( censusDAOList );
    }

    public String getAreaInSqKmWiseSortedCensusData() throws CensusAnalyserException {
        censusDAOList = censusDAOMap.values().stream().collect( Collectors.toList() );
        Comparator < CensusDAO > censusComparator = Comparator.comparing( CensusDAO :: getAreaInSqKm );
        this.sort( censusDAOList, censusComparator );
        return new Gson().toJson( censusDAOList );
    }

    public String getDensityPerSqKmWiseSortedCensusData() throws CensusAnalyserException {
        censusDAOList = censusDAOMap.values().stream().collect( Collectors.toList() );
        Comparator < CensusDAO > censusComparator = Comparator.comparing( CensusDAO :: getDensityPerSqKm );
        this.sort( censusDAOList, censusComparator );
        return new Gson().toJson( censusDAOList );
    }

    // sort any list in ascending order
    private < E > void sort( List < E > list, Comparator < E > censusComparator ) throws CensusAnalyserException {
        if ( list == null || list.size() == 0 )
            throw new CensusAnalyserException( "No Data", CensusAnalyserException.ExceptionType.NO_DATA );
        for ( int i = 0; i < list.size() - 1; i++ ) {
            for ( int j = 0; j < list.size() - i - 1; j++ ) {
                E census1 = list.get( j );
                E census2 = list.get( j + 1 );
                if ( censusComparator.compare( census1, census2 ) > 0 ) {
                    list.set( j, census2 );
                    list.set( j + 1, census1 );
                }
            }
        }
    }
}
