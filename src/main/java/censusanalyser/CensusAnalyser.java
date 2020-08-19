package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public Integer loadIndiaCensusData( String csvFilePath ) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            CsvToBeanBuilder < IndiaCensusCSV > csvToBeanBuilder = new CsvToBeanBuilder <>( reader );
            csvToBeanBuilder.withType( IndiaCensusCSV.class );
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace( true );
            CsvToBean < IndiaCensusCSV > csvToBean = csvToBeanBuilder.build();
            Iterator < IndiaCensusCSV > censusCSVIterator = csvToBean.iterator();
            Iterable < IndiaCensusCSV > csvIterable = () -> censusCSVIterator;
            return ( Integer ) ( int ) StreamSupport.stream( csvIterable.spliterator(), false ).count();
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        } catch ( RuntimeException e ){
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.NOT_CSV_TYPE_OR_INVALID_HEADER );
        }
    }
}
