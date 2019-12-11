<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){
        //创建表单
        $("#usTable").jqGrid({
            url : "${path}/user1/selectAll",   //page  当前页    rows 每页展示条数
            //editurl:"/user/edit",
            datatype : "json",
            rowNum : 2,
            rowList : [2,5,10,20,30],
            pager : '#usPage',
            viewrecords : true,  //是否展示总条数
            styleUI:"Bootstrap",
            height:"auto",
            autowidth:true,
            colNames : [ 'Id', '头像','状态','名字', '法名' , '所在地',  '注册时间'],
            colModel : [
                {name : 'id',index : 'id',width : 55},

                {name : 'avatar',editable:true,index : 'invdate',width : 90,align:"center",edittype:"file",
                    formatter:function(cellvalue){
                        return "<img src='${path}/upload/photo/"+cellvalue+"' style='width:180px;height:80px' />";
                    }
                },
                {name : 'status',index : 'amount',width : 80,align : "right",align:"center",
                    formatter:function(cellvalue,option,rowObject){
                        if(cellvalue==1){
                            return "<button class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")'>不展示</button>";
                        }else{
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")'>展示</button>";
                        }
                    }
                },
                {name : 'name',index : 'id',width : 55},
                {name : 'law_name',index : 'id',width : 55},
                {name : 'city',editable:true,index : 'name asc, invdate',width : 100},
                {name : 'crea_date',index : 'note',width : 150,sortable : false}
            ]
        });

        //增删改查操作
        $("#usTable").jqGrid('navGrid', '#usPage', {edit : false,add : false,del : false,search:false,addtext:"添加",edittext:"编辑"},
            {
                closeAfterEdit:true, //关闭添加框
            },   //修改之后的额外操作
            {
                closeAfterAdd:true, //关闭添加框
            },   //添加之后的额外操作
            {}    //删除之后的额外操作
        );

    });
    //点击删除按钮
    function daoData(){
            $.ajax({
                url:"${path}/user1/daoData",
                type:"post",
                datatype:"json",
                success:function(){
                    //刷新表单
                   alert("导出成功");
                }
            });
    }
    //点击状态按钮
    function updateStatus(id,status){

        if(status=="0"){
            $.ajax({
                url:"${path}/user1/edit",
                type:"post",
                datatype:"json",
                data:{"id":id,"status":"1","oper":"edit"},
                success:function(){
                    //刷新表单
                    $("#usTable").trigger("reloadGrid");
                }
            });
        }else{
            $.ajax({
                url:"${path}/user1/edit",
                type:"post",
                datatype:"json",
                data:{"id":id,"status":"0","oper":"edit"},
                success:function(){
                    //刷新表单
                    $("#usTable").trigger("reloadGrid");
                }
            });
        }
    }
</script>

<%--初始化面板--%>
<div class="panel panel-success">

    <%--面板标题--%>
    <div class="panel panel-heading">
        <h3>用户管理</h3>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a >用户管理</a></li>
    </ul>
        <div class="panel panel-body">
            <button id="b1" class="btn btn-info" onclick="daoData()">导出数据</button>&emsp;
        </div>

    <%--初始化表单--%>
    <table id="usTable"/>

    <%--分页工具栏--%>
    <div id="usPage" />

</div>