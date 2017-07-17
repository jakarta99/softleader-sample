package tw.com.softleader.sample.work;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class Work {
    private Long id;

    private String name;

    private JPerson jPerson;

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", name='" + name + '}';
    }

    public JPerson getjPerson() {
        return jPerson;
    }

    public void setjPerson(JPerson jPerson) {
        this.jPerson = jPerson;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
