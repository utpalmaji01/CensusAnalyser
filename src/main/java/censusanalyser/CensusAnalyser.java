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
            Iterator < IndiaCensusCSV > censusCSVIterator = this.getCSVFileIterator( reader, IndiaCensusCSV.class );
            return this.getCount( censusCSVIterator );
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }

    public Integer loadIndianStateCodeData( String csvFilePath ) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) );
            Iterator < IndianStateCodeCSV > stateCodeCSVIterator = this.getCSVFileIterator( reader, IndianStateCodeCSV.class );
            return this.getCount( stateCodeCSVIterator );
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }
    }

    private < E > Iterator < E > getCSVFileIterator( Reader reader, Class csvClass ) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder < E > csvToBeanBuilder = new CsvToBeanBuilder <>( reader );
            csvToBeanBuilder.withType( csvClass );
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace( true );
            CsvToBean < E > csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch ( RuntimeException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.NOT_CSV_TYPE_OR_INVALID_HEADER_OR_DELIMITER );
        }
    }

    private < E > Integer getCount( Iterator < E > iterator ) {
        Iterable < E > csvIterable = () -> iterator;
        return ( Integer ) ( int ) StreamSupport.stream( csvIterable.spliterator(), false ).count();

    }
}
