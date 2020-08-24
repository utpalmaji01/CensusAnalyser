package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

    @CsvBindByName( column = "State Name", required = true )
    public String stateName;

    @CsvBindByName( column = "Population", required = true )
    public int population;

    @CsvBindByName( column = "AreaInSqKm", required = true )
    public int areaInSqKm;

    @CsvBindByName( column = "DensityPerSqKm", required = true )
    public int densityPerSqKm;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "StateName='" + stateName + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
