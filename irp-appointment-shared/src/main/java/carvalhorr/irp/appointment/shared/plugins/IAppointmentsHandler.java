package carvalhorr.irp.appointment.shared.plugins;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentsHandler {

    public void handleAppointments(List<LocalDateTime> availableSlots);

}
