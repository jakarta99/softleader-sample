package tw.com.softleader.sample.work;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
@Entity
@Table(name = "WORK")
public class Work {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JPERSON_ID")
    private JPerson jPerson;



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
