package discuz.com.net.service.model.bean.compileBeans.defaults;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 */

public class DefaultField {
    private List<Header> header;
    private List<Signature> message;

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public List<Signature> getMessage() {
        return message;
    }

    public void setMessage(List<Signature> message) {
        this.message = message;
    }
}
