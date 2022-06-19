package com.petterp.latte_core.util.callback;


import androidx.annotation.Nullable;

/**
 * @ author Zhenyu on 2022/5/15
 * Summary:回调
 * email： 1023927274@qq.com
 */
public interface IGlobalCallback <T>{
    void executeCallback(@Nullable T args);
}
