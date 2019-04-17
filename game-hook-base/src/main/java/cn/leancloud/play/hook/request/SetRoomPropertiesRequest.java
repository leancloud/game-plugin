package cn.leancloud.play.hook.request;

import clojure.lang.Keyword;
import clojure.lang.PersistentHashMap;
import clojure.lang.RT;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public final class SetRoomPropertiesRequest extends AbstractRequest {
    private static final Keyword expect_attr_k = (Keyword) RT.keyword(null, "expect-attr");
    private static final Keyword attr_k = (Keyword) RT.keyword(null, "attr");

    public SetRoomPropertiesRequest(Map<Keyword, Object> requestParams) {
        super(requestParams);
    }

    /**
     * 设置房间自定义属性
     *
     * @param attr 房间自定义属性，不能是空也不能是 null。attr 会被拷贝一份后存入请求内，
     *             所以本方法返回后再修改 attr 不会影响已存入请求内的房间自定义属性参数
     * @return this
     */
    public SetRoomPropertiesRequest setProperties(Map<String, Object> attr) {
        Objects.requireNonNull(attr);
        if (attr.isEmpty()) throw new IllegalArgumentException();

        setParameter(attr_k, PersistentHashMap.create(attr));
        return this;
    }

    /**
     * 获取房间自定义属性
     *
     * @return 返回房间玩家自定义属性，是不可变 Map
     */
    public Map<String, Object> getProperties() {
        return getParameter(attr_k, Collections.emptyMap());
    }

    /**
     * 获取 CAS 操作用于匹配的房间自定义属性。设置了用于匹配的自定义属性后，只有当房间自定义属性符合
     * 匹配的值后更新房间自定义属性操作才会生效。
     *
     * @return 用于匹配的房间自定义属性
     */
    public Map<String, Object> getExpectedValues() {
        return getParameter(expect_attr_k, Collections.emptyMap());
    }

    /**
     * 设置 CAS 操作用于匹配的房间自定义属性。设置了用于匹配的自定义属性后，只有当房间自定义属性符合
     * 匹配的值后更新房间自定义属性操作才会生效。
     *
     * @param casAttr 用于匹配的房间自定义属性，不能是空也不能是 null。casAttr 会被拷贝一份后存入请求内，
     *                所以本方法返回后再修改 casAttr 不会影响已存入请求内的属性参数
     * @return this
     */
    public SetRoomPropertiesRequest setExpectedValues(Map<String, Object> casAttr) {
        Objects.requireNonNull(casAttr);
        if (casAttr.isEmpty()) throw new IllegalArgumentException();

        setParameter(expect_attr_k, PersistentHashMap.create(casAttr));
        return this;
    }
}