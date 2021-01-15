package fr.uge.lootin.back;

import org.hibernate.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class GameDAO {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public long create(String gameName) {
        return lambdaSession(session ->{
            var game = new Game(gameName);


            session.persist(game);
            return game.getId();
        });
    }

    /**
     * Remove the employee with the given id from the DB
     * @param id
     * @return true if the removal was successful
     */

    public boolean delete(long id) {
        return lambdaSession(session ->{
            var game = session.get(Game.class, id);
            if (game == null) return false;
            session.delete(game);
            return true;
        });
    }




    public boolean update(Game game) {
        return lambdaSession(session ->{
            session.merge(game);
            return true;
        });
    }

    /**
     * Retrieve the employee with the given id
     * @param id
     * @return the employee wrapped in an {@link Optional}
     */

    public Optional<Game> get(long id) {
        return lambdaSession(session ->{
            var game = session.get(Game.class, id);
            if (game == null) return Optional.empty();
            return Optional.of(game);
        });
    }

    /**
     * Return the list of the employee in the DB
     */

    public List<Game> getAll() {
        return lambdaSession(session ->{
            return session.createQuery("from Game u", Game.class).getResultList();
        });
    }

    public Optional<Game> initAllField(long id){
        return lambdaSession(session -> {
            var game = session.get(Game.class, id);
            if (game == null) return Optional.empty();


            return Optional.of(game);
        });
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
