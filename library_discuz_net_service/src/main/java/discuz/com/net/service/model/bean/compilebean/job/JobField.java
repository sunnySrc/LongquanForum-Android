package discuz.com.net.service.model.bean.compilebean.job;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 * 工作情况
 */

public class JobField {
    private List<Company> company;
    private List<Occupation> occupation;

    public List<Company> getCompany() {
        return company;
    }

    public void setCompany(List<Company> company) {
        this.company = company;
    }

    public List<Occupation> getOccupation() {
        return occupation;
    }

    public void setOccupation(List<Occupation> occupation) {
        this.occupation = occupation;
    }
}
