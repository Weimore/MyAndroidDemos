package com.example.mymodeldemos.application;

import java.security.Key;
import java.util.WeakHashMap;

/**
 * Created by 吴城林 on 2017/8/23.
 */

public class Configurator {

    private static final WeakHashMap<String,Object> APP_CONFIG = new WeakHashMap<>();

    private Configurator() {
        getAppConfig().put(ConfigType.CONFIG_READY.name(),false);
    }

    private static class Holder{
        private static Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public static WeakHashMap<String, Object> getAppConfig() {
        return APP_CONFIG;
    }

    //检查是否配置完成
    private void checkConfigIsReady(){
        boolean isReady = (boolean) APP_CONFIG.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configurate");
        }
    }


    //获得想要的配置信息
    @SuppressWarnings("unchecked")
    public final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfigIsReady();
        return (T) getAppConfig().get(key.name());
    }

    //调用该方法，表示已配置完成
    public final void Configurate(){
        getAppConfig().put(ConfigType.CONFIG_READY.name(),true);
    }
}
