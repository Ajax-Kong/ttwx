package com.fjx.wechat.base.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fjx.common.framework.system.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjx.common.action.BaseController;
import com.fjx.wechat.base.admin.entity.ExtAppEntity;
import com.fjx.wechat.base.admin.service.ExtAppService;


/**
 * 应用扩展
 * @author fengjx xd-fjx@qq.com
 * @version ExtAppController.java 2014年9月13日
 */
@Controller
@RequestMapping("/admin/extapp")
public class ExtAppController extends BaseController {
	
	@Autowired
	private ExtAppService extAppService;
	
	@RequestMapping(value={"","/"})
	public String view(HttpServletRequest request) {
		return "/wechat/admin/sys/busiapp";
	}
	
	/**
	 * 查询接口列表
	 * @param app_type
	 * @param msg_type
	 * @param event_type
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public List<ExtAppEntity> listByType(String app_type, String msg_type, String event_type){
		List<ExtAppEntity> list = extAppService.listByType(app_type,msg_type,event_type);
		return list;
	}


	/**
	 * 分页查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pageList")
	@ResponseBody
	public Pagination<ExtAppEntity> pageList(HttpServletRequest request){
		return extAppService.page();
	}



}
