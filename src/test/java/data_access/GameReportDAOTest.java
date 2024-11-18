package data_access;

import org.junit.Test;

import static data_access.GameReportDAOConstants.MOVE_QUALITY;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

public class GameReportDAOTest {

    @Test
    public void specificUserTest() throws FileNotFoundException {
        String dataPath = "src/main/java/user_data/Martha.csv";
        GameReportDataAccessObject marthaData = new GameReportDataAccessObject(dataPath);
        assertEquals("scrumptious", marthaData.getStatistic(2,3,MOVE_QUALITY));
    }
}
