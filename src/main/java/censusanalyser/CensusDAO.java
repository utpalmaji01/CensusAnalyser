package censusanalyser;

public class CensusDAO {
    private String stateName;
    private int population;
    private int areaInSqKm;
    private int densityPerSqKm;
    private String stateCode;
    private int tin;
    private double area;
    private double density;


    public CensusDAO( IndiaCensusCSV indiaCensusCSV ) {
        stateName = indiaCensusCSV.stateName;
        population = indiaCensusCSV.population;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO( IndianStateCodeCSV indianStateCodeCSV ) {
        stateName = indianStateCodeCSV.stateName;
        stateCode = indianStateCodeCSV.stateCode;
        tin = indianStateCodeCSV.tin;
    }

    public CensusDAO( USCensusCSV usCensusCSV ) {
        stateName = usCensusCSV.stateName;
        stateCode = usCensusCSV.stateCode;
        population = usCensusCSV.population;
        density = usCensusCSV.density;
        area = usCensusCSV.area;
    }

    public String getStateName() {
        return stateName;
    }

    public int getPopulation() {
        return population;
    }

    public int getAreaInSqKm() {
        return areaInSqKm;
    }

    public int getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public String getStateCode() {
        return stateCode;
    }

    public int getTin() {
        return tin;
    }
}
