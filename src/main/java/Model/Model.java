package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prash_000
 */
public class Model {
    private Map<String, Object> user;

    /**
     * Constructor
     */
    public Model() {
        this.user = new HashMap<>();
    }
    
    /**
     * This function Creates a new User
     * @param id
     * @param fname
     * @param mname
     * @param lname
     * @param age
     * @param gender
     * @param phone
     * @param zip
     * @return
     */
    public int createUser(String id, String fname, String mname, String lname, int age, char gender, String phone, long zip) {
        UserTable usr = new UserTable();
        usr.setId(id);
        usr.setFirstName(fname);
        usr.setMiddleName(mname);
        usr.setLastName(lname);
        usr.setAge(age);
        usr.setGender(gender);
        usr.setPhone(phone);
        usr.setZip(zip);
        user.put(id,usr);
        return 1;
    }
    
    /**
     * Check to find if a user is available
     * @param id
     * @return
     */
    public boolean checkUser(String id) {
        Iterator it = user.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            UserTable u = (UserTable)pair.getValue();
            if((u.getId().equals(id)))
                return false;
        }
        return true;
    }
    
    /**
     * Similar function to update a user
     * @param id
     * @param fname
     * @param mname
     * @param lname
     * @param age
     * @param gender
     * @param phone
     * @param zip
     * @return
     */
    public int updateUser(String id, String fname, String mname, String lname, int age, char gender, String phone, long zip) {
        UserTable usr = (UserTable)user.get(id);
        usr.setFirstName(fname);
        usr.setMiddleName(mname);
        usr.setLastName(lname);
        usr.setAge(age);
        usr.setGender(gender);
        usr.setPhone(phone);
        usr.setZip(zip);
        user.put(id,usr);
        return 1;
    }

    public boolean removeUser(String id) {
        if(!checkUser(id)) {
            user.remove(id);
            return true;    
        }
        return false;
    }
    
    /**
     * This function creates a list of all users
     * @return
     */
    public List sendElements() {
        List<Object> ret = new ArrayList<>(user.values());
        return ret;
    }

    public List sendUsersId() {
        List<Object> ret = new ArrayList<>(user.keySet());
        return ret;
    }
}
