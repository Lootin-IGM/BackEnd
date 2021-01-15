package fr.uge.lootin.back;

import org.hibernate.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserDAO {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public long create(String firstName, String lastName, Set<Game> games, Login login) {
        return lambdaSession(session ->{
            var s = new User(firstName, lastName, games, login);


            session.persist(s);
            return s.getId();
        });
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */

    public boolean delete(long id) {
        return lambdaSession(session ->{
            var e = session.get(User.class, id);
            if (e == null) return false;
            session.delete(e);
            return true;
        });
    }




    public boolean update(User s) {
        return lambdaSession(session ->{
            session.merge(s);
            return true;
        });
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<User> get(long id) {
        return lambdaSession(session ->{
            var e = session.get(User.class, id);
            if (e == null) return Optional.empty();
            return Optional.of(e);
        });
    }

    /**
     * Return the list of the employee in the DB
     */

    public List<User> getAll() {
        return lambdaSession(session ->{
            return session.createQuery("from User u", User.class).getResultList();
        });
    }

    public List<User> getAllInit() {
        return lambdaSession(session ->{
            var tmp = session.createQuery("from User u", User.class).getResultList();
            return tmp.stream().map(u -> {
                initFields(u);
                return u;
            }).collect(Collectors.toList());
        });
    }

    public Optional<User> initAllField(long id){
        return lambdaSession(session -> {
            var user = session.get(User.class, id);
            if (user == null) return Optional.empty();
            initFields(user);

            return Optional.of(user);
        });
    }

    //to use only inside lambdaSession
    private void initFields (User user){
        Hibernate.initialize(user.getGames());
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
