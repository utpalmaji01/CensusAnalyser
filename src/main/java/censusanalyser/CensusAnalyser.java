package censusanalyser;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;
import com.google.gson.Gson;

import java.util.Comparator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Reader;
import java.util.List;
import java.util.stream.StreamSupport;


public class CensusAnalyser {

    List < IndiaCensusDAO > censusList;

    public CensusAnalyser() {
        this.censusList = new ArrayList < IndiaCensusDAO >();
    }

    // load Indian Census data and analyse
    public Integer loadIndiaCensusData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < IndiaCensusCSV > csvFileIterator = csvBuilder.getCSVFileIterator( reader,
                    IndiaCensusCSV.class );
            while ( csvFileIterator.hasNext() ) {
                this.censusList.add( new IndiaCensusDAO( csvFileIterator.next() ) );
            }
            return censusList.size();
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        } catch ( IllegalArgumentException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_TYPE_FILE,
                    e.getCause() );
        } catch ( CSVBuilderException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    e.type.name() );
        }
    }

    // load Indian State Code data and analyse
    public Integer loadIndianStateCodeData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < IndianStateCodeCSV > csvFileIterator = csvBuilder.getCSVFileIterator( reader,
                    IndianStateCodeCSV.class );
            while ( csvFileIterator.hasNext() ) {
                this.censusList.add( new IndiaCensusDAO( csvFileIterator.next() ) );
            }
            return censusList.size();
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        } catch ( IllegalArgumentException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_TYPE_FILE,
                    e.getCause() );
        } catch ( CSVBuilderException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    e.type.name() );
        }
    }

    // count number of records
    private < E > Integer getCount( Iterator < E > iterator ) {
        Iterable < E > csvIterable = () -> iterator;
        return ( Integer ) ( int ) StreamSupport.stream( csvIterable.spliterator(),
                false ).count();
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        Comparator < IndiaCensusDAO > censusComparator = Comparator.comparing( IndiaCensusDAO :: getStateName );
        this.sort( censusList, censusComparator );
        return new Gson().toJson( censusList );
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        Comparator < IndiaCensusDAO > censusComparator = Comparator.comparing( IndiaCensusDAO :: getPopulation );
        this.sort( censusList, censusComparator );
        return new Gson().toJson( censusList );
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        Comparator < IndiaCensusDAO > censusComparator = Comparator.comparing( IndiaCensusDAO :: getStateCode );
        this.sort( censusList, censusComparator );
        return new Gson().toJson( censusList );
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
