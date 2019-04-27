package carvalhorr.irp.appointment.plugins.console;

import carvalhorr.irp.appointment.shared.plugins.IAppointmentsHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsoleWriterPlugin implements IAppointmentsHandler {

    @Override
    public void handleAppointments(List<LocalDateTime> list) {
        list.forEach((LocalDateTime date) -> {
            System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        });
    }

}
