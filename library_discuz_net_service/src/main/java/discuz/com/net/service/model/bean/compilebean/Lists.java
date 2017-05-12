package discuz.com.net.service.model.bean.compilebean;

import discuz.com.net.service.model.bean.compilebean.Contact.Contact;
import discuz.com.net.service.model.bean.compilebean.defaults.Defaults;
import discuz.com.net.service.model.bean.compilebean.education.Education;
import discuz.com.net.service.model.bean.compilebean.job.Job;
import discuz.com.net.service.model.bean.compilebean.userInfo.UserInfo;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 */

public class Lists {
    java.util.List<Defaults> defaults;
    java.util.List<Contact> contacts;
    java.util.List<Education> educations;
    java.util.List<Job> jobs;
    java.util.List<UserInfo> userInfos;

    public java.util.List<Defaults> getDefaults() {
        return defaults;
    }

    public void setDefaults(java.util.List<Defaults> defaults) {
        this.defaults = defaults;
    }

    public java.util.List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(java.util.List<Contact> contacts) {
        this.contacts = contacts;
    }

    public java.util.List<Education> getEducations() {
        return educations;
    }

    public void setEducations(java.util.List<Education> educations) {
        this.educations = educations;
    }

    public java.util.List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(java.util.List<Job> jobs) {
        this.jobs = jobs;
    }

    public java.util.List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(java.util.List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
