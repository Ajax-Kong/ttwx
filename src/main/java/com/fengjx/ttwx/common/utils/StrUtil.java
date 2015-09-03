
package com.fengjx.ttwx.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String����
 *
 * @Created by FengJianxin on 2015/9/3.
 * @Email xd-fjx@qq.com
 */
public class StrUtil extends StringUtils {


    /**
     * ɾ���ո񡢻س����Ʊ��
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
