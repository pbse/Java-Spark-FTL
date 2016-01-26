package User;

/**
 *
 * @author prash_000
 */
public class User implements Validable{
    
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private char gender;
    private String phone;
    // Optional Parameter
    private String middleName = "";
    private long zip = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }
    
    @Override
    public boolean isValid() {
        // check for null
        if(id == null || firstName == null || lastName == null || phone == null)
            return false;
        if(id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty())
            return false;
        if(!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            return false;
        }
        if(id.length()>8 || age<=0 || !(gender == 'M' || gender == 'F') || phone.length() != 10 || !phone.matches("\\d+"))
        {
            return false;
        }
        
        // Check for the optional Parameter to be Alphabet
        if(!middleName.isEmpty()) {
            if(!middleName.matches("[a-zA-Z]+"))
                return false;
        }
        return true;
    }
    
}
