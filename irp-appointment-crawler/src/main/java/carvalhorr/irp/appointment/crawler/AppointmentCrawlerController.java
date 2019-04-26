package carvalhorr.irp.appointment.crawler;

import carvalhorr.irp.appointment.config.ConfigProvider;
import carvalhorr.irp.appointment.plugins.Plugins;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentCrawlerController {

    public static final int THREE_SECONDS_IN_MILISECONDS = 3000;
    private SearchAppointmentsCrawler crawler;
    private boolean keepSearching;

    public void initialize() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL(ConfigProvider.getSeleniumWebDriverUrl()), DesiredCapabilities.chrome());
        AppointmentDataExtractor dataExtractor = new AppointmentDataExtractor();

        crawler = new SearchAppointmentsCrawler(driver, dataExtractor, ConfigProvider.getInisWebsiteUrl());
        keepSearching = true;
    }

    public void start() throws InterruptedException {
        crawler.openWebsite();
        while (keepSearching) {
            List<LocalDateTime> appointments = crawler.search();
            Plugins.notifyAppointments(appointments);
            Thread.sleep(THREE_SECONDS_IN_MILISECONDS);
        }
    }

    public void end() {
        keepSearching = false;
    }

}
