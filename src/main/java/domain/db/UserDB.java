package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDB {
    private Map<String, Person> users = new HashMap<String, Person>();

    public UserDB() {
        addUser(new Person("Elke", "Elke.Steegmans@ucll.be", "t"));
        addUser(new Person("Mieke", "Mieke.Kemme@ucll.be", "t"));
    }

    public void addUser(Person user) {
        users.put(user.getEmail(), user);
    }

    public List<Person> getUsers() {
        return new ArrayList<Person>(users.values());
    }

    public boolean existUser(String email, String password) {
        return (users.containsKey(email) && users.get(email).getPassword().equals(password));
    }

    public Person getUserWithName(String name) {
        for (Person p : users.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}