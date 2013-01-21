/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.updateManagementAgent.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 *
 * @author Martin
 */
public class UpdateActivator implements BundleActivator {

//    private BundleContext bundleContext;
    private File file = null;
    private TimerTask task;
    private URL url = null;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Update Managrement Agent...");
        new Updater();
//        updateBundles(context);
//        final BundleContext bundleContext = context;
//        try {
//            task = new TimerTask() {
//
//                @Override
//                public void run() {
//                    updateBundles(bundleContext);
//                }
//            };
//        } catch (Exception exception) {
//            System.err.println(exception);
//        }
//
//        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.DAYS);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping " + context.getBundle().getSymbolicName() + " budle...");
    }

    private void updateBundles(BundleContext context) {
        System.out.println("Checkging for updates...");
        for (Bundle bundle : context.getBundles()) {
            if ((bundle.getBundleId() != context.getBundle().getBundleId())) {
                final String location = bundle.getLocation();
                try {
                    url = new URL(location);
                } catch (MalformedURLException exception) {
                    continue;
                }
                file = new File(url.getFile());
                if (file.exists()) {
                    Date lastModifiedDate =
                            new Date(bundle.getLastModified());
                    Calendar targetDate = Calendar.getInstance();
                    targetDate.add(Calendar.MINUTE, -1);
                    if (lastModifiedDate.before(targetDate.getTime())) {
                        try {
                            bundle.stop();
                            bundle.update();
                            System.out.println("Bunlde " + bundle.getSymbolicName() + " successfuly updated");
                        } catch (BundleException bundleException) {
                        }
                    } else {
                        System.out.println("No new updates available for " + bundle.getSymbolicName());
                    }
                }
            }
        }
    }
}
