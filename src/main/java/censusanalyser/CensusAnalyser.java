package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    // load Indian Census data and analyse
    public Integer loadIndiaCensusData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < IndiaCensusCSV > censusCSVIterator = csvBuilder.getCSVFileIterator( reader, IndiaCensusCSV.class );
            return this.getCount( censusCSVIterator );
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        } catch (CSVBuilderException e ) {
            throw new CensusAnalyserException( e.getMessage(),e.type.name());
        }
    }

    // load Indian State Code data and analyse
    public Integer loadIndianStateCodeData( String csvFilePath ) throws CensusAnalyserException {
        try ( Reader reader = Files.newBufferedReader( Paths.get( csvFilePath ) ) ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator < IndianStateCodeCSV > stateCodeCSVIterator = csvBuilder.getCSVFileIterator( reader,
                                                                                            IndianStateCodeCSV.class );
            return this.getCount( stateCodeCSVIterator );
        } catch ( IOException e ) {
            throw new CensusAnalyserException( e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM );
        }catch ( CSVBuilderException e ) {
            throw new CensusAnalyserException( e.getMessage(),e.type.name() );
        }
    }

    // count number of records
    private < E > Integer getCount( Iterator < E > iterator ) {
        Iterable < E > csvIterable = () -> iterator;
        return ( Integer ) ( int ) StreamSupport.stream( csvIterable.spliterator(), false ).count();
    }
}
