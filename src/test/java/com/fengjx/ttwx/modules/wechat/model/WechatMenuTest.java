
package com.fengjx.ttwx.modules.wechat.model;

import com.fengjx.ttwx.common.utils.JsonUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @author fengjx.
 * @date：2015/6/9 0009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class WechatMenuTest {

    @Autowired
    private WechatMenu wechatMenu;

    @Test
    public void testTreeMenu() {
        List<Map<String, Object>> treeMenu = wechatMenu
                .treeMenu("93f75794fc6e11e480826036dd68230b");
        System.out.println(JsonUtil.toJson(treeMenu));
    }

    @Test
    public void testRelease() {
        wechatMenu.release("93f75794fc6e11e480826036dd68230b");
    }

}
