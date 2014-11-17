package com.fjx.wechat.base.admin.service;

import java.util.List;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.ExtAppEntity;


/**
 * 应用扩展
 * @author fengjx xd-fjx@qq.com
 * @version ExtAppService.java 2014年9月13日
 */
public interface ExtAppService extends IBaseAbstractService<ExtAppEntity> {
	
	/**
	 * 根据类型查询
	 * @param app_type 应用类型 web、api、restful
	 * @param msg_type
	 * @param event_type
	 * @return
	 */
	public List<ExtAppEntity> listByType(String app_type, String msg_type, String event_type);

	/**
	 * 分页查询
	 * @param extApp
	 * @return
	 */
	public Pagination<ExtAppEntity> page(ExtAppEntity extApp);


}
