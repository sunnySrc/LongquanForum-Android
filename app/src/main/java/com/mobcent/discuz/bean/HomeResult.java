package com.mobcent.discuz.bean;

import java.util.List;

/**
 * Created by sun on 2016/8/23.
 * 首页数据
 */

public class HomeResult extends BaseResult<discuz.com.net.service.model.BaseResult<List>> {
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
    public static class Body {
        public Component module;
    }

    public  List<Component> getComponentList(){
        return body.module.getComponentList();
    }


}
