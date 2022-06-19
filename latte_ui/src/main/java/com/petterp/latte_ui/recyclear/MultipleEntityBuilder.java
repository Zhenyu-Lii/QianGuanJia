package com.petterp.latte_ui.recyclear;

import java.util.LinkedHashMap;

/**
 * @ author Zhenyu on 2019/4/23
 * 邮箱：1023927274@qq.com
 */
public class MultipleEntityBuilder {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //先清除之前的数据
        FIELDS.clear();
    }


    public final MultipleEntityBuilder setItemType(int Type) {
        FIELDS.put(MultipleFidls.ITEM_TYPE, Type);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setField(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
