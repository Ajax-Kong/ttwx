<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<style type="text/css">
	<!--
	.textarea {
		width: 507px;
		height: 120px;
		resize: none;
	}
	-->
</style>
<script type="text/javascript">
	var extType = '${param.extType}';
	var req_type = '${param.req_type}';
	var event_type = '${param.event_type}';
</script>
<script src="<%=resourceUrl %>/script/wechat/admin/action_inc.js?v=2015060501" type="text/javascript" charset="UTF-8"></script>
<form id="msgActionForm" method="post" class="form-inline" role="form">
	<!-- 这个参数区分是更新还是保存 -->
	<input type="hidden" id="editType" name="editType" value="">

	<input type="hidden" id="msgActionId" name="id" value="">
	<input type="hidden" id="extType" name="ext_type" value="">
	<input type="hidden" id="msgReqType" name="req_type" value="">
	<input type="hidden" id="eventType" name="event_type" value="">
	<input type="hidden" id="msgActionType" name="action_type" value="">
	<input type="hidden" id="msgRespType" name="materiaMsgType" value="">
	<input type="hidden" id="msgMaterialId" name="material_id" value="">
	<input type="hidden" id="msgExtAppId" name="app_id" value="">

	<c:if test="${param.req_type eq 'text' }">
	<div class="control-group">
		<label class="control-label divider">关键字：</label>
		<input id="msgKeyWord" name="key_word" value="" class="form-control" type="text" placeholder="用户发送的文字">
		<hr/>
	</div>
	</c:if>

	<c:if test="${param.event_type eq 'CLICK' }">
		<input type="hidden" id="menuId" name="menuId" value="">
		<input type="hidden" id="menuType" name="menuType" value="">
		<input type="hidden" id="menuUrl" name="menuUrl" value="">
	</c:if>
	<input type="hidden" id="txtContent" name="materiaContent" value="">
</form>

<div style="height:390px;margin-top: 10px;">
	<ul id="edit_tabs" class="nav nav-tabs" role="tablist">
		<li class="active">
			<a href="#tab1" data-toggle="tab">
				<i class="glyphicon glyphicon-text-width"></i>
				文字
			</a>
		</li>
		<li>
			<a href="#tab2" data-toggle="tab">
				<i class="glyphicon glyphicon-picture"></i>
				图片
			</a>
		</li>
		<li>
			<a href="#tab3" data-toggle="tab">
				<i class="glyphicon glyphicon-volume-up"></i>
				语音
			</a>
		</li>
		<li>
			<a href="#tab4" data-toggle="tab">
				<i class="glyphicon glyphicon-facetime-video"></i>
				视频
			</a>
		</li>
		<li>
			<a href="#tab5" data-toggle="tab">
				<i class="glyphicon glyphicon-list-alt"></i>
				图文
			</a>
		</li>
		<li>
			<a href="#tab6" data-toggle="tab">
				<i class="glyphicon glyphicon-resize-small"></i>
				扩展应用
			</a>
		</li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="tab1">
			<div style="width: 510px">
				<div id="js_textArea">
					<textarea class="textarea field" id="replyText" name="replyText" ></textarea>
				</div>
				<div class="clear"></div>
				<div id="txt_btn" style="float: left;">
					<c:if test="${ !('hide' eq param.btn_return) }">
					 <span class="btn btn_input btn_default">
						<button onclick="showActionContent('action_index')">返回</button>
					 </span>
					</c:if>
				 <span class="btn btn_input btn_primary">
					<button onclick="submitMsgActionForm('text')">保存</button>
				 </span>
				</div>
			</div>
		</div>
		<div class="tab-pane" id="tab2">
			<p>暂不支持</p>
		</div>
		<div class="tab-pane" id="tab3">
			<p>暂不支持</p>
		</div>
		<div class="tab-pane" id="tab4">
			<p>暂不支持</p>
		</div>
		<div class="tab-pane" id="tab5">
			<input class="field" type="hidden" id="newsId" value="">
			<div style="width: 100%;">
				<label class="control-label">图文预览</label>
				<div class="clear"></div>
				<div style="float: left;">
					<div id="preview_news" style="min-height:300px;max-height:500px; width:350px;float: left; border: solid 1px #E0ECFF;">
						<!-- js加载 预览效果 -->
					</div>
					<div style="height:35px; float: left;margin-top: 270px; ">
						<span class="btn btn_input btn_default">
							<input type="hidden" id="newsId" name="newsId" style="width:250px;">
							<button onclick="openMaterialDialog();">选择</button>
						 </span>
							<c:if test="${ !('hide' eq param.btn_return) }">
							 <span class="btn btn_input btn_default">
								<button onclick="showActionContent('action_index');">返回</button>
							 </span>
							</c:if>
						 <span class="btn btn_input btn_primary">
							<button onclick="submitMsgActionForm('news');">保存</button>
						 </span>
					 </div>
				</div>
				<%--<div class="clear"></div>
                <div id="txt_btn" style="float: left;">
                     <span class="btn btn_input btn_default">
                        <button onclick="showActionContent('action_index');">返回</button>
                     </span>
                     <span class="btn btn_input btn_primary">
                        <button onclick="submitMsgActionForm('news');">保存</button>
                     </span>
                </div> --%>
			</div>
		</div>
		<div class="tab-pane" id="tab6">
			<div class="form-inline" style="width: 500px;">
				<div class="form-group">
					<label class="control-label">选择扩展插件：</label>
					<jsp:include page="/admin/sys/ext/selecter">
						<jsp:param name="showAll" value="1"/>
						<jsp:param name="id" value="busiapp_id"/>
						<jsp:param name="name" value="busiapp_id"/>
						<jsp:param name="app_type" value="api"/>
						<jsp:param name="msg_type" value=""/>
						<jsp:param name="event_type" value=""/>
					</jsp:include>
				</div>
				<div style="height:200px;border: solid 1px #E0ECFF;">
					<!-- js加载 预览效果 -->
				</div>
				<div id="txt_btn" style="float: left;">
					<c:if test="${ !('hide' eq param.btn_return) }">
					 <span id="js_preview" class="btn btn_input btn_default">
						<button onclick="showActionContent('action_index');">返回</button>
					 </span>
					</c:if>
					 <span id="js_submit" class="btn btn_input btn_primary">
						<button onclick="submitMsgActionForm('api');">保存</button>
					 </span>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="materialDialog" style="display: none;">
	<div style="width:99.5%;">

	</div>
</div>
<div class="modal" id="materialModal" tabindex="-1" role="dialog" aria-labelledby="materialModalLabel"
	 aria-hidden="true">
	<div class="modal-dialog" style="width:800px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="materialModalLabel">选择素材</h4>
			</div>
			<div class="modal-body">
				<div style="width:99.5%;height:450px;overflow:auto;">
					<jsp:include page="/WEB-INF/view/wechat/admin/inc_material_list.jsp">
						<jsp:param name="showType" value="select"/>
					</jsp:include>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button onclick="selectedMaterial();" type="button" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>




