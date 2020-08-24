package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCodeCSV {
    @CsvBindByName( column = "SrNo", required = true )
    public int serialNo;

    @CsvBindByName( column = "State Name", required = true )
    public String stateName;

    @CsvBindByName( column = "TIN", required = true )
    public int tin;

    @CsvBindByName( column = "StateCode", required = true )
    public String stateCode;

    @Override
    public String toString() {
        return "IndianStateCodeCSV{" +
                "serialNo='" + serialNo + '\'' +
                ", stateName='" + stateName + '\'' +
                ", tin='" + tin + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }

}
