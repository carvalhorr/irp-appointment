package carvalhorr.irp.appointment;

import carvalhorr.irp.appointment.crawler.AppointmentCrawlerController;
import carvalhorr.irp.appointment.shared.plugins.IAppointmentsHandler;

import java.net.MalformedURLException;

public class CrawlerApplication {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        AppointmentCrawlerController crawlerController = new AppointmentCrawlerController();
        crawlerController.initialize();
        crawlerController.beginCrawling();
    }

}
