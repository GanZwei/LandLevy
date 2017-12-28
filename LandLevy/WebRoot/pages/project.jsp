<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="Description" content=""/>
<meta name="keywords" content=""/>
<title>青海省征地信息公开系统-批后征地信息</title>
<link type="text/css" rel="stylesheet" href="../themes/base.css" />
<link type="text/css" rel="stylesheet" href="../themes/default.css" />
<link type="text/css" rel="stylesheet" href="../javascript/chosen/chosen.min.css" />
<link type="text/css" rel="stylesheet" href="../javascript/artDialog-master/css/ui-dialog.css" />
<script type="text/javascript" src="../javascript/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../javascript/common.js"></script>
<script type="text/javascript" src="../javascript/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="../javascript/artDialog-master/dist/dialog-plus-min.js"></script>
<script type="text/javascript">
var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    $(document).ready(function(){
   					 for (var selector in config) {
			   		 $(selector).chosen(config[selector]);
			   		 }
					$('.chosen-select').chosen();      
    });
function checkboxSelect(obj){
	if($(obj).hasClass("selected")){
		$(obj).removeClass("selected");
		$("input",$(obj)).attr("checked",false);
		}
	else{
		$(obj).addClass("selected");
		$("input",$(obj)).attr("checked", "checked");
		}
	}
$(function(){
	$('.delPlan').on('click',
    function() {
        var d = dialog({
            title: '删除确认',
            content: '',
            cancelValue: '取消',
            okValue: '确定',
            ok: function() {},

            cancel: function() {}
        });
        var elem = $("#confirmBox");
        d.content(elem).showModal();
    });
	});
	 function querycounty(){
	 	$(".countys").empty();
	 			for (var selector in config) {
			    $(selector).chosen(config[selector]);
			    }
				$('.chosen-select').chosen();
		var city=$("#city option:selected").text();
		$.post("../Notice/querycounty",{"cityname":city},function(resp,status){
			var data=eval("("+resp.data+")");
			$("#county").chosen("destroy");
			for(var i=0;i<data.length;i++){
				 $("#county").append("<option class='countys' value='"+data[i]+"'>"+data[i]+"</option>");
			}
			   for (var selector in config) {
			    $(selector).chosen(config[selector]);
			    }
				$('.chosen-select').chosen();
		},"json");
	}
	 function queryvillage(){
	 	$(".villages").empty();
	 			for (var selector in config) {
			    $(selector).chosen(config[selector]);
			    }
				$('.chosen-select').chosen();
		var county=$("#county option:selected").text();
		$.post("../Notice/queryvillage",{"countyname":county},function(resp,status){
			var data=eval("("+resp.data+")");
			$("#village").chosen("destroy");
			for(var i=0;i<data.length;i++){
				 $("#village").append("<option class='villages' value='"+data[i]+"'>"+data[i]+"</option>");
			}
			   for (var selector in config) {
			    $(selector).chosen(config[selector]);
			    }
				$('.chosen-select').chosen();
		},"json");
	}
	   
</script>
</head>

<body>
<div class="header">
  <h1 class="logoText">青海省征地信息公开系统</h1>
  <div class="menuGroup">
    <ul>
      <li><a href="default.html" class="home"><i></i>首页</a></li>
      <li><a href="javascript:;" onclick="showMessage(this)" class="message"><i><em class="iconState"></em></i>待办提醒</a></li>
      <li><a href="setting.html" class="setting"><i></i>系统设置</a></li>
      <li class="i_user"><a href="user.html" class="user"><i></i>我的账号</a>
        <div class="userbox"> <span class="username"><em>西宁市大通县</em>张三：欢迎您！</span>
          <div class="s_group">
            <div class="it_group"><a href="changepassaord.html" class="grayColor">修改密码</a> </div>
            <div class="it_group"><a href="login.html" class="s_btn">退出系统</a></div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</div>
<div class="content">
<div class="menu">
    <ul>
      <li><a href="javascript:;" class="all"><i></i><span>所有项目</span></a></li>
      <li><a href="project.html" class="active"><i></i><span>待办项目</span></a></li>
      <li><a href="project.html"><i></i><span>待审核项目</span></a></li>
      <li><a href="project.html"><i></i><span>待发布项目</span></a></li>
      <li><a href="project.html" class="end"><i></i><span>已发布项目</span></a></li>
    </ul>
  </div>
  
  <div class="wrp hasleft clearfix">
 <form action="<%=request.getContextPath() %>/Notice/query">
    <div class="functionBar clearfix">
      <div class="selectionGroup">
        <div class="dropDown"  style="width:90px;" >
          <select  name="city" data-placeholder="市、州" class="chosen-select-no-single" tabindex="9" id="city" onchange="querycounty()"">
            <option value=""></option>
            <c:if test="${empty sessionScope.city }">
	            <c:redirect url="../Notice/querycity">
	            	<c:param name="city" value="全部"></c:param>
	            </c:redirect>
            </c:if>
            <option value="United States">所有</option>
            <c:forEach items="${ sessionScope.city}" var="city" begin="0">
            <option value="${city}"><c:out value="${city}"/></option>
            </c:forEach>
            <option value="United States">已发布</option>
            <option value="United States">未发布</option>
          </select>
          
        </div>
      </div>
      <div class="selectionGroup">
        <div class="dropDown"  style="width:120px;">
          <select data-placeholder="所在区" class="chosen-select-no-single" tabindex="9" name="county" id="county" onchange="queryvillage()">
            <option value=""></option>
            <option value="United States">全部</option>
            <option value="United States">已发布</option>
            <option value="United States">未发布</option>
          </select>
        </div>
      </div>
      <div class="selectionGroup">
        <div class="dropDown"  style="width:120px;">
          <select data-placeholder="所在街道" class="chosen-select-no-single" tabindex="9" id="village" name="village">
            <option value=""></option>
            <option value="United States">全部</option>
            <option value="United States">已发布</option>
            <option value="United States">未发布</option>
          </select>
        </div>
      </div>
     
	      <div class="queryGroup"> <span class="searchBox">
	        <input type="text" placeholder="请输入公告名称或文号的关键字" class="searchMain" name="input-1">
	        <a href="javascript:;" class="removeText"><i></i></a>
	        <button class="searchBtn" type="submit" onclick="javascript:window.location.href='<%=request.getContextPath()%>/Notice/query'"><i></i></button>
	        </span> 
	        <input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
	        <input type="hidden" name="pageSize" value="10"/>
	        </div>
    </div>
   </form>
    <div class="dataWrap">
      <div class="dataGrid">
        <div class="gridMain">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th><span class="btnChose ctrlChosen" onclick="checkboxSelect(this)">
                <input name="" type="checkbox" checked="checked" value="" />
                <i></i><em>全选</em></span></th>
              <th>项目名称</th>
              <th>批准文号</th>
              <th>项目位置</th>
              <th>批准年度</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
    	 <c:if test="${!empty sessionScope.list }">
    	    <c:forEach items="${sessionScope.list }" var="list">
            <tr>
				 <td><span class="btnChose ctrlChosen selected" onclick="checkboxSelect(this)">
                <input name="" type="checkbox" value="" />
                <i></i></span></td>
              	<c:if test="${list.status=='省厅发起'}">
              		 <td><a href="projectDetail.jsp" class="heightColor"><c:out value="${list.apply}"></c:out></a></td>
              	</c:if>
              		<c:if test="${list.status=='县级填报'}">
              		 <td><a href="projectDetail01.html" class="heightColor"><c:out value="${list.apply}"></c:out></a></td>
              	</c:if>	 
              		<c:if test="${list.status=='市（州）级填报'}">
              		 <td><a href="projectDetail02.html" class="heightColor"><c:out value="${list.apply}"></c:out></a></td>
              	</c:if>	 
              		<c:if test="${list.status=='省厅审核'}">
              		 <td><a href="projectDetail03.html" class="heightColor"><c:out value="${list.apply}"></c:out></a></td>
              	</c:if>	 
              		<c:if test="${list.status=='发布成功'}">
              		 <td><a href="projectDetail04.html" class="heightColor"><c:out value="${list.apply}"></c:out></a></td>
              	</c:if>	 	 
             		 <td><c:out value="${list.reference}"></c:out></td>
             		 <td><c:out value="${list.address}"></c:out></td>
             		 <td><c:out value="${list.year}"></c:out></td>
             		 <td><c:out value="${list.status}"></c:out></td> 	
              <td><span class="heightColor moreTips">详情<i class="tipsIcon"></i>
                <div class="menuGroupBox">
                  <ul>
                    <li><a href="projectDetail.html">详情</a></li>
                    <li><a href="javascript:;">发布</a></li>
                    <li><a href="javascript:;" class="delPlan">删除</a></li>
                  </ul>
                </div>
                </span></td>
            </tr>
         </c:forEach>
       </c:if> 
            <tr>
              <td><span class="btnChose ctrlChosen selected" onclick="checkboxSelect(this)">
                <input name="" type="checkbox" value="" />
                <i></i></span></td>
              <td><a href="projectDetail01.html" class="heightColor">2017年度西商村拟征收土地公告</a></td>
              <td>ABC-123456</td>
              <td>三坐标</td>
              <td>2015</td>
              <td>县级填报</td>
              <td><span class="heightColor moreTips">详情<i class="tipsIcon"></i>
                <div class="menuGroupBox">
                  <ul>
                    <li><a href="projectDetail01.html">详情</a></li>
                    <li><a href="javascript:;">发布</a></li>
                    <li><a href="javascript:;" class="delPlan">删除</a></li>
                  </ul>
                </div>
                </span></td>
            </tr>
            <tr>
              <td><span class="btnChose ctrlChosen selected" onclick="checkboxSelect(this)">
                <input name="" type="checkbox" value="" />
                <i></i></span></td>
              <td><a href="projectDetail02.html" class="heightColor">2017年度西商村拟征收土地公告</a></td>
              <td>ABC-123456</td>
              <td>三坐标</td>
              <td>2015</td>
              <td>市（州）级填报</td>
              <td><span class="heightColor moreTips">详情<i class="tipsIcon"></i>
                <div class="menuGroupBox">
                  <ul>
                    <li><a href="projectDetail02.html">详情</a></li>
                    <li><a href="javascript:;">发布</a></li>
                    <li><a href="javascript:;" class="delPlan">删除</a></li>
                  </ul>
                </div>
                </span></td>
            </tr>
           <tr>
              <td><span class="btnChose ctrlChosen selected" onclick="checkboxSelect(this)">
                <input name="" type="checkbox" value="" />
                <i></i></span></td>
              <td><a href="projectDetail03.html" class="heightColor">2017年度西商村拟征收土地公告</a></td>
              <td>ABC-123456</td>
              <td>三坐标</td>
              <td>2015</td>
              <td>省厅审核</td>
              <td><span class="heightColor moreTips">详情<i class="tipsIcon"></i>
                <div class="menuGroupBox">
                  <ul>
                    <li><a href="projectDetail03.html">详情</a></li>
                    <li><a href="javascript:;">发布</a></li>
                    <li><a href="javascript:;" class="delPlan">删除</a></li>
                  </ul>
                </div>
                </span></td>
            </tr>
          <tr>
              <td><span class="btnChose ctrlChosen selected" onclick="checkboxSelect(this)">
                <input name="" type="checkbox" value="" />
                <i></i></span></td>
              <td><a href="projectDetail04.html" class="heightColor">2017年度西商村拟征收土地公告</a></td>
              <td>ABC-123456</td>
              <td>三坐标</td>
              <td>2015</td>
              <td>发布成功</td>
              <td><span class="heightColor moreTips">详情<i class="tipsIcon"></i>
                <div class="menuGroupBox">
                  <ul>
                    <li><a href="projectDetail04.html">详情</a></li>
                    <li><a href="javascript:;">发布</a></li>
                    <li><a href="javascript:;" class="delPlan">删除</a></li>
                  </ul>
                </div>
                </span></td>
            </tr>
          </table>
        </div>
      </div>
      <ul class="pagination">
        <li class="gotoPage">
          <input type="text">
          <a class="pageBtn">跳转</a> </li>
        <li> <a class="pageBtn nextPage"><i class="arrow"></i></a> </li>
        <li> <span class="pageNum"><em class="cur">1</em><em class="integral">/</em><em class="all">5</em></span> </li>
        <li> <a class="pageBtn prevPage"><i class="arrow"></i></a> </li>
		<li> <span class="pageNum"><em class="cur">每页20条 共20条</em></span> </li>
      </ul>
    </div>
  </div>
</div>
<div class="confirmBox hide" id="confirmBox">
  <div class="uplayoutBox">
    <div class="addTwoLevelbox"> <span class="tips_icon confirm_icon48"></span>
      <div class="confirmBoxRight">
        <h4>删除确认</h4>
        <p>删除后该公告将不能恢复。</p>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
    
	</script>
<div class="messageBox">
<div class="messageBoxContent">
  <div class="messageMain">
    <ul>
      <li class="messageItem">
        <div class="messageTime"> <i class="lineBg"></i><b class="timePoint"></b><p class="timeBlock">2017-06-05<br />21:21</p></div>
        <div class="messageBlock">
        <h2 class="messageItemTitle">2017年度西商村拟征收土地公告</h2>
        <p class="cmessageContent">项目由省厅发起，项目位置西宁市上东区，项目文号ABC-123456。</p>
        <a class="messageTodo" href="plan.html">立即处理</a>
        </div>
      </li>
      <li class="messageItem">
        <div class="messageTime"> <i class="lineBg"></i><b class="timePoint"></b><p class="timeBlock">2017-06-05<br />21:21</p></div>
        <div class="messageBlock">
        <h2 class="messageItemTitle">2017年度西商村拟征收土地公告</h2>
        <p class="cmessageContent">项目由省厅发起，项目位置西宁市上东区，项目文号ABC-123456。</p>
        <a class="messageTodo" href="plan.html">立即处理</a>
        </div>
      </li>
      <li class="messageItem">
        <div class="messageTime"> <i class="lineBg"></i><b class="timePoint"></b><p class="timeBlock">2017-06-05<br />21:21</p></div>
        <div class="messageBlock">
        <h2 class="messageItemTitle">2017年度西商村拟征收土地公告</h2>
        <p class="cmessageContent">项目由省厅发起，项目位置西宁市上东区，项目文号ABC-123456。</p>
        <a class="messageTodo" href="plan.html">立即处理</a>
        </div>
      </li>
    </ul><div class="loadMore">
    <a href="javascript:;">加载更多</a>
    </div>
  </div>
  </div>
  <a class="closeMessageBox" href="javascript:;" onClick="closeMessage(this)"></a>
 <script type="text/javascript">
 function closeMessage(obj){
	 $(obj).parent().slideUp("fast");
	 }
  function showMessage(){
	  $(".messageBox").slideToggle("fast");
	  }
 </script>
</div>
</body>
</html>
