package cn.leancloud.play.hook.request;

import clojure.lang.Keyword;
import clojure.lang.PersistentHashMap;
import clojure.lang.RT;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public final class SetPlayerPropertiesRequest extends AbstractRequest {
    private static final Keyword target_actor_id_k = (Keyword) RT.keyword(null, "target-actor-id");
    private static final Keyword expect_attr_k = (Keyword) RT.keyword(null, "expect-attr");
    private static final Keyword attr_k = (Keyword) RT.keyword(null, "attr");

    public SetPlayerPropertiesRequest(Map<Keyword, Object> requestParams) {
        super(requestParams);
    }

    /**
     * 获取房间玩家自定义属性
     *
     * @return 返回房间玩家自定义属性，是不可变 Map
     */
    public Map<String, Object> getProperties() {
        return getParameter(attr_k, Collections.emptyMap());
    }

    /**
     * 设置房间玩家自定义属性
     *
     * @param attr 房间玩家自定义属性，不能是空也不能是 null。attr 会被拷贝一份后存入请求内，
     *             所以本方法返回后再修改 attr 不会影响已存入请求内的房间玩家自定义属性参数
     * @return this
     */
    public SetPlayerPropertiesRequest setProperties(Map<String, Object> attr) {
        Objects.requireNonNull(attr);
        if (attr.isEmpty()) throw new IllegalArgumentException();

        setParameter(attr_k, PersistentHashMap.create(attr));
        return this;
    }

    /**
     * 获取 CAS 操作用于匹配的玩家自定义属性。设置了用于匹配的自定义属性后，只有当玩家在房间内属性符合
     * 匹配的值后更新玩家属性操作才会生效。
     *
     * @return 用于匹配的玩家自定义属性
     */
    public Map<String, Object> getExpectedValues() {
        return getParameter(expect_attr_k, Collections.emptyMap());
    }

    /**
     * 设置 CAS 操作用于匹配的玩家自定义属性。设置了用于匹配的自定义属性后，只有当玩家在房间内属性符合
     * 匹配的值后更新玩家属性操作才会生效。
     *
     * @param casAttr 用于匹配的玩家自定义属性，不能是空也不能是 null。casAttr 会被拷贝一份后存入请求内，
     *                所以本方法返回后再修改 casAttr 不会影响已存入请求内的属性参数
     * @return this
     */
    public SetPlayerPropertiesRequest setExpectedValues(Map<String, Object> casAttr) {
        Objects.requireNonNull(casAttr);
        if (casAttr.isEmpty()) throw new IllegalArgumentException();

        setParameter(expect_attr_k, PersistentHashMap.create(casAttr));
        return this;
    }

    /**
     * 获取修改自定义属性的目标玩家 Actor Id
     *
     * @return 目标玩家 Actor Id
     */
    public int getTargetActorId() {
        Number id = getParameter(target_actor_id_k);
        if (id != null) {
            return id.intValue();
        } else {
            return -1;
        }
    }

    /**
     * 设置修改自定义属性的目标玩家 Actor Id
     *
     * @param actorId 目标玩家 Actor Id
     * @return this
     */
    public SetPlayerPropertiesRequest setTargetActorId(int actorId) {
        setParameter(target_actor_id_k, (long) actorId);
        return this;
    }
}
