package fr.uge.lootin.back;

import org.hibernate.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MessageDAO {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public long create(Match match, Timestamp sendTime, String s) {
        return lambdaSession(session ->{
            var message = new Message(match, sendTime, s);
            session.persist(message);
            return message.getId();
        });
    }

    public long create(Match match, String s) {
        return lambdaSession(session ->{
            var message = new Message(match, s);
            session.persist(message);
            return message.getId();
        });
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */
    public boolean delete(long id) {
        return lambdaSession(session ->{
            var message = session.get(Message.class, id);
            if (message == null) return false;
            session.delete(message);
            return true;
        });
    }




    public boolean update(Message message) {
        return lambdaSession(session ->{
            session.merge(message);
            return true;
        });
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<Message> get(long id) {
        return lambdaSession(session ->{
            var message = session.get(Message.class, id);
            if (message == null) return Optional.empty();
            return Optional.of(message);
        });
    }

    /**
     * Return the list of the employee in the DB
     */
    public List<Message> getAll() {
        return lambdaSession(session ->{
            return session.createQuery("select m from Message m", Message.class).getResultList();
        });
    }

    public List<Message> getAllFromMatch(long machId) {
        return lambdaSession(session ->{
            var match = session.get(Match.class, machId);





            return session.createQuery("select m from Message m where :mat = m.match", Message.class).setParameter("mat", match).getResultList();
        });
    }

    public Optional<Message> initAllField(long id){
        return lambdaSession(session -> {
            var message = session.get(Message.class, id);
            if (message == null) return Optional.empty();
            initFields(message);

            return Optional.of(message);
        });
    }

    //to use only inside lambdaSession
    private void initFields (Message message){
        Hibernate.initialize(message.getMatch());
        Hibernate.initialize(message.getMatch().getUser1());
        Hibernate.initialize(message.getMatch().getUser2());
        Hibernate.initialize(message.getMatch().getUser1().getGames());
        Hibernate.initialize(message.getMatch().getUser2().getGames());
    }


    private <T> T lambdaSession(Function<Session, T> fun){
        T res = null;
        Transaction tx = null;



        try(Session session = sessionFactory.openSession()) {
            tx= session.beginTransaction();
            res = fun.apply(session);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        return res;
    }
}
