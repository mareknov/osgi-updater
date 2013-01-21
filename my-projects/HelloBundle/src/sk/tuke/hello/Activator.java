/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.hello;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 * @author Martin
 */
public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext bc) throws Exception {
        System.out.println("Hello OSGi Bundle");
    }

    @Override
    public void stop(BundleContext bc) throws Exception {
        System.out.println("Stopping bundle ...");
    }
    
}
