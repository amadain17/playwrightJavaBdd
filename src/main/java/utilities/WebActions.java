package utilities;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.microsoft.playwright.Locator;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static org.apache.poi.ss.usermodel.WorkbookFactory.create;

@Slf4j
public abstract class WebActions {

    public static String getProperty(String key) {
        ConfigReader configReader = new ConfigReader();
        Properties properties = configReader.initProp();    // Reading from config properties file
        return properties.getProperty(key);
    }

    public static boolean waitUntilElementDisplayed(Locator locator, int timeoutSec) {
        boolean elementVisible = locator.isVisible();
        int timer = 0;
        while (!elementVisible && timer < timeoutSec) {
            try {
                Thread.sleep(1000);
                elementVisible = locator.isVisible();
                timer++;

            } catch (Exception e) {
                log.error(locator + "was not visible.");
            }
        }
        return elementVisible;
    }

    public static String getRowColValue(String filePath, String sheetName, int rowNum, int colNum) {
        String fileName = System.getProperty("user.dir") + "/src/main/java/utils/functional/" + filePath + ".xlsx";
        Workbook workbook = null;
        try {
            workbook = create(new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
    }
    
    public static String validateQRFromFile(String filename) throws IOException, NotFoundException {
		BufferedImage bufferedImage = ImageIO.read(new File("target/qrsnaps/"+filename+".png"));
		LuminanceSource luminanceSource=  new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		Result result = new MultiFormatReader().decode(binaryBitmap);
		log.info(result.getText());
		return result.getText();
	}
}