package fr.uge.lootin.back;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Function;

public class HibernateUtils {
    static final private SessionFactory sessionFactory;
    static final private StandardServiceRegistry registry;

    // thread-safe implementation of the singleton pattern
    static {
        // Create sessionFactory from the hibernate.cfg.xml file
        registry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources sources = new MetadataSources(registry);
        Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    static public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}