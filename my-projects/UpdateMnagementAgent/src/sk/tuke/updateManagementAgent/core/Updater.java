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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 *
 * @author Martin
 */
public class Updater {

    private BundleContext context;
    private Configuration configuration;
    private File file = null;
    private URL url = null;

    public Updater() {
        try {
            updateBundles(getConfiguredBundles());
        } catch (ConfigurationException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateBundles(List configuredBundles) {
        for (Object object : configuredBundles) {
            System.out.println(object.toString());
            for (Bundle bundle : context.getBundles()) {
                if (bundle.getSymbolicName().startsWith(object.toString())) {
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
                                bundle.update();
                                System.out.println("Bunlde " + bundle.getSymbolicName() + " successfuly updated");
                            } catch (BundleException bundleException) {
                                Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, bundleException);
                            }
                        } else {
                            System.out.println("No new updates available for " + bundle.getSymbolicName());
                        }
                    }
                }
            }

        }

    }

    private List getConfiguredBundles() throws ConfigurationException {
        configuration = new PropertiesConfiguration("config.properties");
        List bundleList = configuration.getList("configured-bundle");
        if (bundleList != null) {
            return bundleList;
        }
        return null;
    }
}
