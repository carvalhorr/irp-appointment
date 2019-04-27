package carvalhorr.irp.appointment.plugins;

import carvalhorr.irp.appointment.config.ConfigProvider;
import carvalhorr.irp.appointment.shared.plugins.IAppointmentsHandler;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class Plugins {

    public static final String JAR_EXTENSION = ".jar";
    private static final Plugins instance = new Plugins();
    private ServiceLoader<IAppointmentsHandler> loader;

    static {
        instance.loadPlugins();
    }

    public static void notifyAppointments(List<LocalDateTime> availableAppointments) {
        instance.loader.reload();
        Iterator<IAppointmentsHandler> appointmentsHandlerIterator = instance.loader.iterator();
        while (appointmentsHandlerIterator.hasNext()) {
            appointmentsHandlerIterator.next().handleAppointments(availableAppointments);
        }
    }

    private Plugins() {
    }

    private void loadPlugins() {
        File[] pluginFiles = new File(ConfigProvider.getPluginsPath())
                .listFiles((File dir, String name) -> name.endsWith(JAR_EXTENSION));
        List<URL> urlsList = new ArrayList<>();
        for (File file : pluginFiles) {
            try {
                urlsList.add(file.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        URLClassLoader classLoader = URLClassLoader.newInstance(urlsList.toArray(new URL[]{}),
                Thread.currentThread().getContextClassLoader());
        loader = ServiceLoader.load(IAppointmentsHandler.class,
                classLoader);
    }

}
