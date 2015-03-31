
package com.fengjx.wechat.sdk.fastweixin.api;

import com.fengjx.wechat.sdk.fastweixin.api.config.ApiConfig;
import com.fengjx.wechat.sdk.fastweixin.api.entity.Menu;
import com.fengjx.wechat.sdk.fastweixin.api.enums.ResultType;
import com.fengjx.wechat.sdk.fastweixin.api.response.BaseResponse;
import com.fengjx.wechat.sdk.fastweixin.api.response.GetMenuResponse;
import com.fengjx.wechat.sdk.fastweixin.util.JsonUtil;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单相关API
 *
 * @author peiyu
 * @since 1.2
 */
public class MenuAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(MenuAPI.class);

    public MenuAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单对象
     * @return 调用结果
     */
    public ResultType createMenu(Menu menu) {
        Validate.notNull(menu, "menu is null");
        LOG.debug("创建菜单.....");
        String url = BASE_API_URL + "cgi-bin/menu/create?access_token=#";
        BaseResponse response = executePost(url, menu.toJsonString());
        return ResultType.get(response.getErrcode());
    }

    /**
     * 获取所有菜单
     *
     * @return 菜单列表对象
     */
    public GetMenuResponse getMenu() {
        GetMenuResponse response = null;
        LOG.debug("获取菜单信息.....");
        String url = BASE_API_URL + "cgi-bin/menu/get?access_token=#";

        BaseResponse r = executeGet(url);
        response = JsonUtil.toBean(r.getErrmsg(), GetMenuResponse.class);
        return response;
    }

    /**
     * 删除所有菜单
     * 
     * @return 调用结果
     */
    public ResultType deleteMenu() {
        LOG.debug("删除菜单.....");
        String url = BASE_API_URL + "cgi-bin/menu/delete?access_token=#";
        BaseResponse response = executeGet(url);
        return ResultType.get(response.getErrcode());
    }
}
