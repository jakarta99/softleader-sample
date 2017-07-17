package tw.com.softleader.sample.work;

import java.util.List;

/**
 * Created by JerryLin on 2017/7/9.
 */
public class JCompany {




    private Long id;
    private String name;
    private String tel;
    List<JPerson> jPersonList ;

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
