<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="Description" content=""/>
<meta name="keywords" content=""/>
<title>青海省征地信息公开系统-拟征收土地公告-新增</title>
<link type="text/css" rel="stylesheet" href="../themes/base.css" />
<link type="text/css" rel="stylesheet" href="../themes/default.css" />
<link type="text/css" rel="stylesheet" href="../javascript/chosen/chosen.min.css" />
<link type="text/css" rel="stylesheet" href="../javascript/artDialog-master/css/ui-dialog.css" />
<link type="text/css" rel="stylesheet" href="../javascript/treeTable-1.4.2/default/jquery.treeTable.css" />
<script type="text/javascript" src="../javascript/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../javascript/chosen/chosen.jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="../javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../javascript/artDialog-master/dist/dialog-plus-min.js"></script>
<script type="text/javascript" src="../javascript/treeTable-1.4.2/jquery.treeTable.js"></script>
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
			   $(function(){
				    $('.addGroup').on('click',
    function() {
        var d = dialog({
            //title: '添加组',
            content: '',
			follow: this,
            cancelValue: '取消',
            okValue: '确定',
            ok: function() {},
			quickClose: true,
            cancel: function() {}
        });
        var elem = $("#addGroup");
        d.content(elem).show();
    });
	
	$('.editGroup').on('click',
    function() {
        var d = dialog({
            //title: '添加组',
			width:480,
            content: '',
			follow: this,
            cancelValue: '取消',
            okValue: '确定',
            ok: function() {},
			quickClose: true,
            cancel: function() {}
        });
        var elem = $("#editGroup");
        d.content(elem).show();
    });
	
   $(".dellinks").on('click',
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
				   })

               
	});
$(function(){
	
			$('#addSamples').on('click', function () {
		var d = dialog({
			title: '添加器具',
			content: '',
			cancelValue: '取消',
			okValue: '确定',
			ok: function () {
			},
			
			cancel: function () {}
		});
        var elem = $("#addSampleBox");
		d.content(elem).showModal();
	});
	 var option = {
                theme:'vsStyle',
                expandLevel : 2,
                beforeExpand : function($treeTable, id) {
                    //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                    if ($('.' + id, $treeTable).length) { return; }
                    //这里的html可以是ajax请求
                    var html = '<tr id="8" pId="6"><td><span class="tree_icon"><b class="treeName">碾伯镇</b></span></td><td></td><td align="right"><a href="javascript:;" class="normalLink editGroup">编辑</a><a href="javascript:;" class="normalLink dellinks">删除</a></td></tr>'
                             + '<tr id="9" pId="6"><td><span class="tree_icon"><b class="treeName">碾伯镇</b></span></td><td></td><td align="right"><a href="javascript:;" class="normalLink editGroup">编辑</a><a href="javascript:;" class="normalLink dellinks">删除</a></td></tr>';

                  // $treeTable.addChilds(html);
                },
                onSelect : function($treeTable, id) {
                    window.console && console.log('onSelect:' + id);
                    
                }

            };
            $('#treeTable1').treeTable(option);

            option.theme = 'default';
            $('#treeTable2').treeTable(option);
	});
	<!--------------------自己写的开始部分------------------>
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
	function addnotice (){
		var constructionType=$("#constructionType").val();
		var starTime=$("#starTime").val();
		var applyname=$("#applyname").val();
		var reference=$("#reference").val();
		var year=$("#year").val();
		var city=$("#city").val();
		var county=$("#county").val();
		var village=$("#village").val();
		var burg=$("#burg").val();
		var constructionType=$("#constructionType").val();
		$.post("../Notice/addNotice",{constructionType:constructionType,starTime:starTime,applyname:applyname,
		reference:reference,year:year,city:city,county:county,village:village,burg:burg
		})
	}
	<!--------------------自己写的结束部分------------------>
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
  <div class="wrp clearfix">
    <div class="crumbs clearfix"><span class="backTo"><a href="javascript:history.go(-1)" class="backToIcon"></a><a class="backLinks heightColor" href="javascript:history.go(-1)">拟征收土地公告</a><b class="partLine grayColor">/</b><b>新增拟征收土地公告</b></span></div>
    <div class="processWrap">
      <div class="processBox fiveStep"><!--定义步数，系统自动分配宽度-->
        <div class="percentage"></div>
        <div class="pocessLine"> <span class="processPoint active"> <em class="percentage"></em> <i class="pointIcon"></i> <b class="ponitText">省厅发起项目</b> </span> <span class="processPoint"> <em class="percentage"></em> <i class="pointIcon"></i> <b class="ponitText">县级填报</b> </span> <span class="processPoint"> <em class="percentage"></em> <i class="pointIcon"></i> <b class="ponitText">市（州）填报</b> </span> <span class="processPoint"> <em class="percentage"></em> <i class="pointIcon"></i> <b class="ponitText">省厅同意发布</b> </span> <span class="processPoint"> <em class="percentage"></em> <i class="pointIcon"></i> <b class="ponitText">发布成功</b> </span> </div>
      </div>

    </div>
    <div class="pulicBox">
      <div class="newArticle">
        <div class="msgEditlabel clearfix">
          <div class="halfPart"> <em class="frmLabel">建设用地类型</em>
            <div class="frmInputBox frmInputBoxNoTextLimit">
              <div class="selectionGroup">
                <div class="dropDown"  style="width:220px;">
                  <select data-placeholder="请选择" class="chosen-select-no-single" tabindex="9" id="constructionType">
                    <option value=""></option>
                    <option value="单独选址项目">单独选址项目</option>
                    <option value="城镇分批次项目">城镇分批次项目</option>
                    <option value="城乡建设用地增减挂钩">城乡建设用地增减挂钩</option>
                  </select>
                </div>
              </div>
            </div>
            <!--<div class="pagesTips errorText" style="display:block;">一个字也没有，你还想保存？</div>--> 
          </div>
          <div class="halfPart"> <em class="frmLabel">填报发起时间</em>
            <div class="frmInputBox frmInputBoxNoTextLimit">
              <input type="text" class="textInput"  id="starTime"/>
            </div>
            <!--<div class="pagesTips errorText" style="display:block;">一个字也没有，你还想保存？</div>--> 
          </div>
        </div>
        <div class="msgEditlabel clearfix">
          <div class="halfPart"> <em class="frmLabel">用地项目名称</em>
            <div class="frmInputBox frmInputBoxNoTextLimit">
              <input type="text" class="textInput" id="applyname"/>
            </div>
            <!--<div class="pagesTips errorText" style="display:block;">一个字也没有，你还想保存？</div>--> 
          </div>
          <div class="halfPart"> <em class="frmLabel">批准文号</em>
            <div class="frmInputBox frmInputBoxNoTextLimit">
              <input type="text" class="textInput"  id="reference"/>
            </div>
          </div>
        </div>
        <div class="msgEditlabel clearfix">
          <div class="halfPart"> <em class="frmLabel">批准年度</em>
            <div class="frmInputBox frmInputBoxNoTextLimit">
              <input type="text" class="textInput" onClick="WdatePicker()" id="year"/>
            </div>
            <!--<div class="pagesTips errorText" style="display:block;">一个字也没有，你还想保存？</div>--> 
          </div>
        </div>
        <div class="msgEditlabel clearfix">
          <div class="halfPart"> <em class="frmLabel">项目位置</em>
            <div class="frmInputBox frmInputBoxNoTextLimit">
              <div class="selectionGroup">
                <div class="dropDown"  style="width:120px;">
                  <select data-placeholder="所属地市" class="chosen-select-no-single" tabindex="9" id="city" name="city" onchange="querycounty()">
                    <option value=""></option>
                    <option value="United States">全部</option>
                    <option value="United States">已发布</option>
                    <option value="United States">未发布</option>
                    <c:if test="${empty sessionScope.city }">
	           			<c:redirect url="../Notice/querycity">
	            		  <c:param name="city" value="全部"></c:param>
	           			</c:redirect>
           			</c:if>
           			<c:forEach items="${ sessionScope.city}" var="city" begin="0">
			            <option value="${city}"><c:out value="${city}"/></option>
			        </c:forEach>
                  </select>
                </div>
              </div>
              <div class="selectionGroup">
                <div class="dropDown"  style="width:120px;">
                  <select data-placeholder="所属区县" class="chosen-select-no-single" tabindex="9"  id="county" name="county" onchange="queryvillage()">
                    <option value=""></option>
                    <option value="United States">全部</option>
                    <option value="United States">已发布</option>
                    <option value="United States">未发布</option>
                  </select>
                </div>
              </div>
              <div class="selectionGroup">
                <div class="dropDown"  style="width:120px;">
                  <select data-placeholder="乡镇（街道）" class="chosen-select-no-single" tabindex="9"  id="village" name="village">
                    <option value=""></option>
                    <option value="United States">全部</option>
                    <option value="United States">已发布</option>
                    <option value="United States">未发布</option>
                  </select>
                </div>
              </div>
            </div>
            <!--<div class="pagesTips errorText" style="display:block;">一个字也没有，你还想保存？</div>--> 
          </div>
        </div>
        <div class="msgEditlabel clearfix">
          <div class="halfPart"> <em class="frmLabel">所属村居</em>
            <div class="frmInputBox frmInputBoxNoTextLimit">
              <input type="text" class="textInput" id="burg"/>
            </div>
            <!--<div class="pagesTips errorText" style="display:block;">一个字也没有，你还想保存？</div>--> 
          </div>
        </div>
        <div class="subEditlabelBox">
          <div class="dataGrild treeTable">
            <table id="treeTable1" style="width:100%">
              <tr>
                <th align="left"><span class="fieldBlock leftAlign">文件名称</span></th>
                <th  align="left"><span class="fieldBlock leftAlign">公开类型</span></th>
                <th  align="left"><span class="fieldBlock leftAlign">是否上传</span></th>
                <th  align="left"><span class="fieldBlock rightAlign">操作</span></th>
              </tr>
              <tr id="1">
                <td><span controller="true" class="tree_icon"><b class="treeName">省厅</b></span></td>
                <td></td>
                <td></td>
                <td align="right"></td>
              </tr>
              <tr id="101" pId="1">
                <td><span controller="true" class="tree_icon"><b class="treeName">国务院批准用地</b></span></td>
                <td></td>
                <td></td>
                <td align="right"><a href="javascript:;" class="normalLink addGroup">上传文件</a><a href="javascript:;" class="normalLink editGroup">编辑</a><a href="javascript:;" class="normalLink dellinks">删除文件</a></td>
              </tr>
              <tr id="101" pId="1">
                <td><span controller="true" class="tree_icon"><b class="treeName">省级批准用地</b></span></td>
                <td></td>
                <td></td>
                <td align="right"><a href="javascript:;" class="normalLink addGroup">上传文件</a><a href="javascript:;" class="normalLink editGroup">编辑</a><a href="javascript:;" class="normalLink dellinks">删除文件</a></td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div class="ctrlBar">
      <button class="ctrlBtn">返回</button>
      <button class="ctrlBtn focusBtn" type="submit" onclick="addnotice()">保存</button>
      <button class="ctrlBtn focusBtn">保存并发起</button>
    </div>
  </div>
</div>
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
