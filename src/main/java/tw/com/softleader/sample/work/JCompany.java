package tw.com.softleader.sample.work;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JerryLin on 2017/7/9.
 */
@Entity
@Table(name = "JCOMPANY")
public class JCompany {



    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TEL")
    private String tel;
    @OneToMany
    @JoinColumn(name = "JCOMPANY_ID")
    private List<JPerson> jPersonList ;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setjPersonList(List<JPerson> jPersonList) {
        this.jPersonList = jPersonList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public List<JPerson> getjPersonList() {
        return jPersonList;
    }

    @Override
    public String toString() {
        return "JCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", jPersonList=" + jPersonList +
                '}';
    }
}
