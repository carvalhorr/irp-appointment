package carvalhorr.irp.appointment.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class AppointmentDataExtractor {

    public static final String NO_APPOINTMENT_S_ARE_CURRENTLY_AVAILABLE = "No appointment(s) are currently available";
    public static final String APPOINTMENTS_SELECTOR = "#dvAppOptions table";
    public static final String BOOK_THIS = "Book This";
    public static final String EMPTY_STRING = "";
    public static final String DATE_TIME_FORMAT = "dd MMMM yyyy - kk:mm";

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_FORMAT)
            .withLocale(Locale.US);

    public List<LocalDateTime> extractAppointments(String html) {
        Document document = Jsoup.parse(html);
        Elements el = document.select(APPOINTMENTS_SELECTOR);
        List<LocalDateTime> availableApporintments = new ArrayList<>();
        el.forEach(element -> {
            if (!NO_APPOINTMENT_S_ARE_CURRENTLY_AVAILABLE.equals(element.text())) {
                availableApporintments.add(
                        LocalDateTime.parse(element.text().replace(BOOK_THIS, EMPTY_STRING).trim(), dateTimeFormatter));
            }
        });
        return availableApporintments;
    }

}
