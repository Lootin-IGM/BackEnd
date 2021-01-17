package fr.uge.lootin.back;

import org.hibernate.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MatchDAO {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public long create(User user1, User user2) {
        return lambdaSession(session ->{
            var match = new Match(user1, user2);
            session.persist(match);
            return match.getId();
        });
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */

    public boolean delete(long id) {
        return lambdaSession(session ->{
            var match = session.get(Match.class, id);
            if (match == null) return false;
            session.delete(match);
            return true;
        });
    }




    public boolean update(Match match) {
        return lambdaSession(session ->{
            session.merge(match);
            return true;
        });
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<Match> get(long id) {
        return lambdaSession(session ->{
            var match = session.get(Match.class, id);
            if (match == null) return Optional.empty();
            return Optional.of(match);
        });
    }

    /**
     * Return the list of the employee in the DB
     */

    public List<Match> getAll() {
        return lambdaSession(session ->{
            return session.createQuery("select m from Match m", Match.class).getResultList();
        });
    }

    public Optional<Match> initAllField(long id){
        return lambdaSession(session -> {
            var match = session.get(Match.class, id);
            if (match == null) return Optional.empty();
            initFields(match);

            return Optional.of(match);
        });
    }

    //to use only inside lambdaSession
    private void initFields (Match match){
        Hibernate.initialize(match.getUser1());
        Hibernate.initialize(match.getUser2());
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
