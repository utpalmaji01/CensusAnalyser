package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    @CsvBindByName(column = "State Id",required = true)
    public String stateCode;

    @CsvBindByName(column = "State",required = true)
    public String stateName;

    @CsvBindByName(column = "Population",required = true)
    public int  population;

    @CsvBindByName(column = "Total area",required = true)
    public double  area;

    @CsvBindByName(column = "Population Density",required = true)
    public double  density;

}
