/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import objects.ImdbData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class Conn {

    private static SessionFactory sf;

    public static SessionFactory getSf() {
        if (sf == null || sf.isClosed()) {
            sf = new Configuration().configure().buildSessionFactory();
            try {
                sf = new AnnotationConfiguration().
                        configure().
                        //addPackage("com.xyz") //add package if used.
                        addAnnotatedClass(ImdbData.class).
                        buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sf;
    }

    public static void setSf(SessionFactory sf) {
        Conn.sf = sf;
    }
}
