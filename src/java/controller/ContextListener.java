package controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import logtrack.ExceptionLogTrack;
import model.framework.DataBaseConnections;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(AppConfig.getInstance());
        System.out.println(ExceptionLogTrack.getInstance());
        System.out.println(DataBaseConnections.getInstance());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DataBaseConnections.getInstance().closeAllConnections();
        } catch (Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
        }
    }

}
