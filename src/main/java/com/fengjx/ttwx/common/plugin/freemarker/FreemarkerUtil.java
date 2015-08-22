
package com.fengjx.ttwx.common.plugin.freemarker;

import freemarker.core.ParseException;
import freemarker.template.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;

/**
 * freemark������
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public class FreemarkerUtil {

    private static Configuration cfg;

    protected static void init(Configuration cfg) {
        FreemarkerUtil.cfg = cfg;
    }

    /**
     * @param root
     * @param templatePath
     * @param htmlPath
     * @return �������ɵ��ļ�·��
     */
    public static String createHTML(Map root, String templatePath, String htmlPath) {
        Template template = null;
        String flag = null;
        File htmlFile = null;
        Writer out = null;
        try {
            htmlPath = htmlPath.replaceAll("\\\\", "/");
            htmlFile = new File(htmlPath);
            out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            // ����ģ���ļ�
            template = cfg.getTemplate(templatePath);
            // ����html
            template.process(root, out);
            flag = htmlPath;
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return flag;
    }

    /**
     * ����ftl�ļ��������ַ���
     *
     * @param root
     * @param templatePath
     * @return
     */
    public static String process(Map root, String templatePath) {
        String flag = null;
        Writer out = null;
        Template template = null;
        try {
            out = new StringWriter();
            // ����ģ���ļ�
            template = cfg.getTemplate(templatePath);
            // ����ftl�ļ�
            template.process(root, out);
            flag = out.toString();
        } catch (MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return flag;
    }

}
