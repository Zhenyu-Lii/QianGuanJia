package com.petterp.latte_core.app;

import com.petterp.latte_core.util.storage.LatterPreference;

public class AccouttManager {
    private enum SignTag{
        SIGN_TAG
    }

    /**
     * 保存用户进入状态
     * @param state
     */
    public static void setSignState(boolean state){
        LatterPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    public  static  boolean isSignIn(){
        return LatterPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public  static  void checkAccount(IUserCheker cheker){
        if (isSignIn()){
            cheker.onSignIn();
        }else{
            cheker.onNotSoignIn();
        }
    }
}
