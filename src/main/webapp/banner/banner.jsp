<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){
        //创建表单
        $("#bnTable").jqGrid({
            url : "${path}/banner/selectAll",   //page  当前页    rows 每页展示条数
            editurl:"${path}/banner/edit",
            datatype : "json",
            pager : '#bnPage',
            page:1,
            rowNum : 3,
            rowList : [2,5,10,20,30],
            viewrecords : true,  //是否展示总条数
            styleUI:"Bootstrap",
            height:"auto",
            autowidth:true,
            colNames : [ 'Id', '图片', '描述', '状态', '上传时间'],
            colModel : [
                {name : 'id',index : 'id',width : 55},                                         //上传图
                {name : 'src_img',editable:true,index : 'invdate',width : 90,align:"center",edittype:"file",
                    formatter:function(cellvalue, options, rowObject){
                        return "<img src='${path}/upload/photo/"+cellvalue+"' style='width:180px;height:80px' />";
                    }
                },
                {name : 'description',editable:true,index : 'name asc, invdate',width : 100},
                {name : 'status',index : 'amount',width : 80,align : "right",align:"center",
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "<button class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")'>冻结</button>";
                        }else{
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")'>展示</button>";
                        }
                    }
                },
                {name : 'upload_time',index : 'note',width : 150,sortable : false}
            ]
        });
        //增删改查操作
        $("#bnTable").jqGrid('navGrid', '#bnPage', {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑"},
            {
                closeAfterEdit:true, //关闭添加框

                beforeShowForm:function(obj){
                    obj.find("#src_img").attr("disabled",true);
                }
            },   //修改之后的额外操作
            {
                closeAfterAdd:true, //关闭添加框
                afterSubmit:function (data) {  //提交之后执行的方法
                    //文件的上传
                    $.ajaxFileUpload({
                        url:"${path}/banner/uploadBanner",
                        type:"post",
                        datatype:"json",
                        data:{id:data.responseText},  //获取id
                        fileElementId:"src_img",  //需要上传的文件域的ID，即<input type="file">的ID
                        success:function(){
                            //刷新表单
                            $("#bnTable").trigger("reloadGrid");
                        }
                    });
                    return "hehe";  //必须要有返回值  返回值随便写
                }
            },   //添加之后的额外操作
            {}    //删除之后的额外操作
        );

    });

    function updateStatus(id,status){
        if(status==1){
            $.ajax({
                url:"${path}/banner/edit",
                type:"post",
                datatype:"json",
                data:{"id":id,"status":2,"oper":"edit"},
                success:function () {
                    $("#bnTable").trigger("reloadGrid");
                }
            })
        }else {
            $.ajax({
                url:"${path}/banner/edit",
                type:"post",
                datatype:"json",
                data:{"id":id,"status":1,"oper":"edit"},
                success:function () {
                    //刷新页面
                    $("#bnTable").trigger("reloadGrid");
                }
            })
        }
    }
</script>

<%--初始化面板--%>
<div class="panel panel-info">

    <%--面板标题--%>
    <div class="panel panel-heading">
        <h3>轮播图管理</h3>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a >轮播图管理</a></li>
    </ul>

    <%--初始化表单--%>
    <table id="bnTable"/>

    <%--分页工具栏--%>
    <div id="bnPage" />

</div>