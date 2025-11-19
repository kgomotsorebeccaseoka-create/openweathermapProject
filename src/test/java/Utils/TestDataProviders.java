package Utils;

import org.testng.annotations.DataProvider;

public class TestDataProviders {


    @DataProvider(name = "RegistrationStation")
    public Object[][] registerStationData() {
        return ReadFromExcel.getSheetData("RegistrationStation");
    }

    @DataProvider(name = "Measurements")
    public static Object[][] measurementsData() {
        return ReadFromExcel.getSheetData("Measurements");
    }
}
