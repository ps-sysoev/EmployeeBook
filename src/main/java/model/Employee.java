package model;

public class Employee {
    private long id;
    private String name;
    private String country;
    // private LocalDateTime localDateTime;

    public Employee() {
    }

    public Employee(String name, String country) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.country = country;

        System.out.printf("An employee with id %s has been created.\n", this.id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
