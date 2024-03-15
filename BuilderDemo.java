class BuilderDemo {
    public static void main(String... rk) {
        // Creating instances of Registration using the Builder pattern
        Registration obj = new Registration.Builder("Ravi Kant Sahu", "er.ravikantsahu").setMobile(89277).build();
        Registration obj2 = new Registration.Builder("Ravi Shanker", "shanker.ravi").setAge(21).build();

        // Printing details of the created objects
        System.out.println(obj.getName() + "\t" + obj.getEmail() + "\t" + obj.getAge() + "\t" + obj.getMobile());
        System.out.println(obj2.getName() + "\t" + obj2.getEmail() + "\t" + obj2.getAge() + "\t" + obj2.getMobile());
    }
}

class Registration {
    // Compulsory fields
    private String name, email;
    
    // Optional fields
    private int age, mobile;

    // Private constructor to enforce the Builder pattern
    private Registration(Builder b) {
        this.name = b.name;
        this.email = b.email;
        this.age = b.age;
        this.mobile = b.mobile;
    }

    // Getter methods for all attributes
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getMobile() {
        return mobile;
    }

    // Builder class to construct Registration objects
    static public class Builder {
        // Compulsory fields in the builder
        private String name, email;
        
        // Optional fields in the builder
        private int age, mobile;

        // Builder constructor with compulsory fields
        public Builder(String name, String mail) {
            this.name = name;
            this.email = mail;
        }

        // Setter methods for optional fields, returning the builder instance for chaining
        public Builder setAge(int a) {
            this.age = a;
            return this;
        }

        public Builder setMobile(int m) {
            this.mobile = m;
            return this;
        }

        // Build method to create an instance of Registration
        public Registration build() {
            return new Registration(this);
        }
    }
}
