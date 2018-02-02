package VideoRecord;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.junit.Test;
import org.monte.media.Format;
import org.monte.media.FormatKeys.*;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

    public class Video_Record
    {

        WebDriver w;

        public ScreenRecorder screenRecorder;



        @Test
        public void test01() throws Exception {
            Video_Record videoRecord = new Video_Record();

            videoRecord.startRecording(); //Started recording


            System.setProperty("webdriver.chrome.driver", "F:/chromedriver.exe");
            w = new ChromeDriver();
            w.manage().window().maximize();
            w.navigate().to("http://localhost:9000");
            w.manage().deleteAllCookies();
            Thread.sleep(2000);

            w.findElement(By.id("userName")).clear();
            Thread.sleep(200);
        /*System.out.println("formatCellValue(excelRead.getEmail()).toString()"+new DataFormatter().formatCellValue(excelRead.getEmail()));*/
            w.findElement(By.id("userName")).sendKeys("User007");
            Thread.sleep(200);

            w.findElement(By.id("password")).clear();
            Thread.sleep(200);

            w.findElement(By.id("password")).sendKeys("User1234568974");
            Thread.sleep(200);

            w.findElement(By.xpath("//html/body/div[2]/div/div/div/div/form/button")).click();
            Thread.sleep(5000);
            videoRecord.stopRecording(); //Stopped recording
        }


        public void startRecording() throws Exception
        {
            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            this.screenRecorder = new ScreenRecorder(gc,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",FrameRateKey, Rational.valueOf(30)),
                    null);
            this.screenRecorder.start();
        }

        public void stopRecording() throws Exception
        {
            this.screenRecorder.stop();
        }

    }

