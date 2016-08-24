package com.mobcent.discuz.base;

/**
 * Created by sun on 2016/8/24.
 */

public interface Tasker {
    void begin();
    void cancel();
    boolean isRunning();

}
