package censusanalyser;

import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    // load Indian Census data and analyse
    public Integer loadIndiaCensusData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            if ( ! csvFilePath.contains( ".csv" ) )
                throw new IllegalArgumentException( "Invalid File Type" );
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List < IndiaCensusCSV > censusCSVList = csvBuilder.getCSVFileList( reader, IndiaCensusCSV.class );
            return censusCSVList.size();
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        } catch ( IllegalArgumentException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_TYPE_FILE,
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
            throw new CensusAnalyserException( e.getMessage(), e.type.name() );
        }
    }

    // count number of records
    private < E > Integer getCount( Iterator < E > iterator ) {
        Iterable < E > csvIterable = () -> iterator;
        return ( Integer ) ( int ) StreamSupport.stream( csvIterable.spliterator(), false ).count();
    }
}
