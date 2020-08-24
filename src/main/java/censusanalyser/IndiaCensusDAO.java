package censusanalyser;

public class IndiaCensusDAO {
    private String stateName;
    private int population;
    private int areaInSqKm;
    private int densityPerSqKm;
    private String stateCode;
    private int tin;


    public IndiaCensusDAO( IndiaCensusCSV indiaCensusCSV ) {
        stateName = indiaCensusCSV.stateName;
        population = indiaCensusCSV.population;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }

    public IndiaCensusDAO( IndianStateCodeCSV indianStateCodeCSV ) {
        stateName = indianStateCodeCSV.stateName;
        stateCode = indianStateCodeCSV.stateCode;
        tin = indianStateCodeCSV.tin;
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
