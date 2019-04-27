package carvalhorr.irp.appointment.config;

public class ConfigProvider {

    public static final String getInisWebsiteUrl() {
        return "https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/AppSelect?OpenForm";
    }

    public static final String getSeleniumWebDriverUrl() {
        return "http://localhost:9515";
    }

    public static final String getPluginsPath() {
        return "/opt/irp-appointment/plugins";
    }

}
