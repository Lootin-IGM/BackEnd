package fr.uge.lootin.back;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        var uDAO = new UserDAO();
        var gDAO = new GameDAO();
        var matchDao = new MatchDAO();
        var msgDao = new MessageDAO();
        var s = new HashSet<Game>();
        var g = gDAO.get(gDAO.create("LoL")).get();
        s.add(g);
        s.add(gDAO.get(gDAO.create("OW")).get());
        s.add(gDAO.get(gDAO.create("COD")).get());

        var id1  = uDAO.create("1", "1", s, new Login("login", "password"));
        var id2  = uDAO.create("2", "2", s, new Login("login", "password"));
        var id3  = uDAO.create("3", "3", s, new Login("login", "password"));
        s.remove(g);

        var user = uDAO.initAllField(id1).get();
        user.setGames(s);
        uDAO.update(user);
        user = uDAO.initAllField(id1).get();
        System.out.println(user);

        var all = uDAO.getAll();
        all.forEach(u -> {
            System.out.println("all : " + uDAO.initAllField(u.getId()).get());
        });

        var idMatch = matchDao.create(all.get(0), all.get(1));
        var idMatch2 = matchDao.create(all.get(1), all.get(2));
        var match = matchDao.initAllField(idMatch).get();
        var match2 = matchDao.initAllField(idMatch2).get();
        msgDao.create(match, "test1");
        msgDao.create(match, "test2");
        msgDao.create(match, "test3");
        msgDao.create(match, "test4");
        msgDao.create(match, "test5");
        msgDao.create(match, "test6");
        msgDao.create(match, "test7");

        msgDao.create(match2, "test8");
        msgDao.create(match2, "test9");
        msgDao.create(match2, "test10");


        System.out.println(uDAO.getAllInit());

        var m = msgDao.getAllFromMatch(idMatch);
        System.out.println(m.size());

        System.out.println(msgDao.getAll().stream().map(u -> msgDao.initAllField(u.getId()).get()).collect(Collectors.toList()));

    }
}
