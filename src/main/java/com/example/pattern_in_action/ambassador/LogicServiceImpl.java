package com.example.pattern_in_action.ambassador;

/**
 * in order to reduce much instances accessed to by many clients
 * we should use singleton pattern there
 */
public class LogicServiceImpl implements LogicService {

    private LogicServiceImpl() {
    }

    public static LogicServiceImpl getInstance() {
        return LogicServiceImplHolder.INSTANCE;
    }

    @Override
    public void doLogic(Integer num) {
        System.err.println("我是第"+num+"client,采用的service是"+getInstance().toString());
    }


    private static class LogicServiceImplHolder {
        private final static LogicServiceImpl INSTANCE = new LogicServiceImpl();
    }
}
