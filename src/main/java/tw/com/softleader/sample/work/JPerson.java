package tw.com.softleader.sample.work;

import java.util.Collection;
import java.util.List;

/**
 * Created by JerryLin on 2017/7/9.
 */
public class JPerson {




    private Long id;
    private String name;
    private Long idno;
    List<Work> works ;
    private JCompany jCompany;

    public void setjCompany(JCompany jCompany) {
        this.jCompany = jCompany;
    }

    public JCompany getjCompany() {

        return jCompany;
    }

    public String getName() {
        return name;
    }

    public Long getIdno() {
        return idno;
    }

    public List<Work> getWorks() {
        return works;
    }

    public Long getId() {

        return id;
    }

    @Override
    public String toString() {
        return "JPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idno=" + idno +
                ", works=" + works +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setIdno(Long idno) {
        this.idno = idno;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
