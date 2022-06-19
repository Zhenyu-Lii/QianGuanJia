package com.petterp.latte_ec.main.login.imodel;

import java.util.HashMap;

/**
 * 用户信息 数据层
 *
 * @ author Zhenyu
 * @date 2022-05-06
 */
public interface IUserModel {

    /**
     * 修改数据
     *
     */
    void updateData(Object key,String value);

    /**
     * 查询数据
     *
     * @return user info
     */
    HashMap<Object, String> queryData();

    /**
     * 保存数据
     */
    void saveData();
}
