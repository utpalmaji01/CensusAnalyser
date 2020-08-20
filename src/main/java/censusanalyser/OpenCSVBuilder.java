package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder {

    // return Iterator for any particular CSV class
    public < E > Iterator < E > getCSVFileIterator( Reader reader,
                                                    Class < E > csvClass )
                                                    throws CensusAnalyserException {
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
}
