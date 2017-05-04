package discuz.com.net.service.model.bean.compilebean.education;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 * 教育程度
 */

public class EducationField {
    private List<GraduateSchool> graduateschool;
    private List<EducationFieldEducated> education;

    public List<GraduateSchool> getGraduateschool() {
        return graduateschool;
    }

    public void setGraduateschool(List<GraduateSchool> graduateschool) {
        this.graduateschool = graduateschool;
    }

    public List<EducationFieldEducated> getEducation() {
        return education;
    }

    public void setEducation(List<EducationFieldEducated> education) {
        this.education = education;
    }
}
