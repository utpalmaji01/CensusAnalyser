package censusanalyser;

import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List < IndiaCensusCSV > censusCSVList = null;

    // load Indian Census data and analyse
    public Integer loadIndiaCensusData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilder.getCSVFileList( reader,
                    IndiaCensusCSV.class );
            return censusCSVList.size();
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
            List < IndianStateCodeCSV > stateCodeCSVList = csvBuilder.getCSVFileList( reader,
                    IndianStateCodeCSV.class );
            return stateCodeCSVList.size();
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
        if ( censusCSVList == null || censusCSVList.size() == 0 )
            throw new CensusAnalyserException( "No Data", CensusAnalyserException.ExceptionType.NO_DATA );
        Comparator < IndiaCensusCSV > censusComparator = Comparator.comparing( census -> census.state );
        this.sort( censusComparator );
        String sortedOnStateJson = new Gson().toJson( censusCSVList );
        return sortedOnStateJson;

    }

    private void sort( Comparator < IndiaCensusCSV > censusComparator ) {
        for ( int i = 0; i < censusCSVList.size() - 1; i++ ) {
            for ( int j = 0; j < censusCSVList.size() - i - 1; j++ ) {
                IndiaCensusCSV census1 = censusCSVList.get( j );
                IndiaCensusCSV census2 = censusCSVList.get( j + 1 );
                if ( censusComparator.compare( census1, census2 ) > 0 ) {
                    censusCSVList.set( j, census2 );
                    censusCSVList.set( j + 1, census1 );
                }
            }
        }
    }
}
