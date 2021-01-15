package fr.uge.lootin.back;

import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        var uDAO = new UserDAO();
        var gDAO = new GameDAO();
        var s = new HashSet<Game>();
        var g = gDAO.get(gDAO.create("LoL")).get();
        s.add(g);
        s.add(gDAO.get(gDAO.create("OW")).get());
        s.add(gDAO.get(gDAO.create("COD")).get());

        var id  = uDAO.create("armand", "liegey", s, new Login("login", "password"));
        s.remove(g);

        var user = uDAO.initAllField(id).get();
        user.setGames(s);
        uDAO.update(user);
        user = uDAO.initAllField(id).get();
        System.out.println(user);

        var all = uDAO.getAll();
        all.forEach(u -> {
            System.out.println(uDAO.initAllField(u.getId()).get());
        });

        System.out.println(uDAO.getAllInit());


    }
}
