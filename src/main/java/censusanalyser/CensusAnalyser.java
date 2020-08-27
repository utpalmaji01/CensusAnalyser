package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    List < CensusDAO > censusDAOList;
    Map < String, CensusDAO > censusDAOMap;

    public enum Country {INDIA, US;}


    public CensusAnalyser() {
        this.censusDAOList = new ArrayList <>();
        this.censusDAOMap = new HashMap <>();
    }

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusDAOMap = new CensusLoader().loadCensusData(country, csvFilePath);
        return censusDAOMap.size();
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
