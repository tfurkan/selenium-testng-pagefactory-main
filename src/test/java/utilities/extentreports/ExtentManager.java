package utilities.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() throws IOException {

        String path = System.getProperty("user.dir")+"/extent-reports/ExtentReport.html";
        final File CONF = new File("config/spark-config.xml");
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.loadXMLConfig(CONF);
        reporter.config().setReportName("Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Taha Furkan AYDOGMUS");
        return extentReports;
    }
}
