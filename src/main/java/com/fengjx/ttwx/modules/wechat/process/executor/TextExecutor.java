
package com.fengjx.ttwx.modules.wechat.process.executor;

import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.api.tuling.client.TulingApiClient;
import com.fengjx.ttwx.modules.api.tuling.vo.req.RequestBean;
import com.fengjx.ttwx.modules.wechat.model.RespMsgAction;
import com.fengjx.ttwx.modules.wechat.process.utils.ExecutorNameUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 文本消息处理器
 *
 * @author fengjx
 * @date 2015-6-24
 */
public class TextExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TextExecutor.class);

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        LogUtil.info(LOG, "进入文本消息处理器fromUserName=" + inMessage.getFromUserName());
        List<Map<String, Object>> keywords = respMsgAction
                .loadKeywordActions(accountRecord.getStr("sys_user_id"));
        Record actionRecord = matching(inMessage.getContent(), keywords);
        // 没有找到匹配规则
        if (null == actionRecord || actionRecord.isEmpty()) {
            String res = extHandel(inMessage);
            if (StringUtils.isNotBlank(res)) { // 如果有数据则直接返回
                return doAction(res);
            }
        }
        return doAction(actionRecord);
    }

    /**
     * 查找匹配规则
     *
     * @param content
     * @param keywords
     * @return
     */
    private Record matching(String content, List<Map<String, Object>> keywords) {
        if (CollectionUtils.isEmpty(keywords)) {
            return null;
        }
        for (Map<String, Object> action : keywords) {
            String fuzzy = action.get("fuzzy") + "";
            String keyword = action.get("key_word") + "";
            // 完全匹配
            if (RespMsgAction.FUZZY_EXACT.equals(fuzzy) && content.equals(keyword)) {
                return new Record(action);
            }
            // 关键字开始
            if (RespMsgAction.FUZZY_START.equals(fuzzy) && content.startsWith(keyword)) {
                return new Record(action);
            }
            // 关键字结束
            if (RespMsgAction.FUZZY_END.equals(fuzzy) && content.endsWith(keyword)) {
                return new Record(action);
            }
            // 包含
            if (RespMsgAction.FUZZY_CONTAIN.equals(fuzzy) && content.contains(keyword)) {
                return new Record(action);
            }
        }
        return null;
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XML_MSG_TEXT, null);
    }

    /**
     * 图灵机器人
     * 
     * @param inMessage
     * @return
     */
    public String extHandel(WxMpXmlMessage inMessage) {
        String fromUserName = inMessage.getFromUserName();
        String content = inMessage.getContent();
        // 没有匹配规则的消息，交给图灵机器人处理
        RequestBean req = new RequestBean();
        req.setInfo(content);
        req.setUserid(fromUserName);
        return TulingApiClient.call2WechatMsg(req);
    }
}
