package com.mobcent.discuz.base;

/**
 * Created by sun on 2016/8/30.
 * 任务清理
 */

public interface TaskCleaner {
    void add(Tasker tasker);
    void clean(Tasker tasker);
}
