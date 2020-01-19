<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        $("#abTable").jqGrid({
            url: "${path}/album/selectAll",
            datatype: "json",
            page: 1,
            rowNum: 8,
            rowList: [8, 10, 20, 30],
            pager: '#abPage',
            viewrecords: true,
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            multiselect: false,
            colNames: ['Id', '标题', "封面", '评分', '作者', '播音', '集数', '内容', '发布日期'],
            colModel: [
                {name: 'id', index: 'id', width: 55},
                {name: 'title', index: 'invdate', width: 90},
                {
                    name: "cover_img", index: "item", width: 130,
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${path}/upload/photo/" + cellvalue + "' style='width:180px;height:80px' />";
                    }
                },
                {name: 'score', index: "score", width: 90},
                {name: 'author', index: 'name', width: 100},
                {name: 'broadcast', index: 'broadcast', width: 90},
                {name: 'count', index: 'amount', width: 80, align: "right"},
                {name: 'content', index: 'tax', width: 80, align: "right"},
                {name: 'pub_date', index: 'note', width: 150, sortable: false}
            ],
            subGrid: true,  //是否开启子表格
            //1.subgrid_id 点击一行会在父表各种创建一个div来容纳子表格  subgrid_id就是这个div的id
            //2.row_id  点击行的id
            subGridRowExpanded: function (subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });

        //父表格 工具栏
        $("#abTable").jqGrid('navGrid', '#abPage', {add: true, edit: true, del: true});
    });

    //子表格
    function addSubGrid(subgridId, rowId) {

        var subgridTableId = subgridId + "Table";
        var pagerId = subgridId + "Page";

        //创建子表格的 table 和分页工具栏
        $("#" + subgridId).html("" +
            "<table id='" + subgridTableId + "' />" +
            "<div id='" + pagerId + "'/>"
        );

        //子表格
        $("#" + subgridTableId).jqGrid({

            url: "${path}/chapter/selectAll?albumId=" + rowId,
            datatype: "json",
            editurl: '${path}/chapter/edit?albumId=' + rowId,
            rowNum: 20,
            pager: "#" + pagerId,
            styleUI: "Bootstrap",
            height: "auto",
            viewrecords: true,
            autowidth: true,
            colNames: ['Id', '标题', '音频', '时长', '大小', '上传时间', "操作"],
            colModel: [
                {name: "id", index: "num", width: 80, key: true},
                {name: "title", index: "item", width: 130},
                {name: "src", index: "qty", edittype: 'file', width: 90, editable: true, align: "right"},
                {name: "duration", index: "unit", width: 70, align: "right"},
                {name: "size", index: "qty", width: 70, align: "right"},
                {name: "upload_time", index: "total", width: 70, align: "right", sortable: false},
                {
                    name: "src", align: "center",
                    formatter: function (cellvalue) {
                        return "<a href='#' onclick='audioDownload(\"" + cellvalue + "\")' ><span class='glyphicon glyphicon-download' /></a>&nbsp;&emsp;&emsp;" +
                            "<a href='#' onclick='audioPlayer(\"" + cellvalue + "\")'><span class='glyphicon glyphicon-play-circle' /></a>";
                    }
                }
            ]
        });


        //子表格的正删改查操作
        $("#" + subgridTableId).jqGrid('navGrid', "#" + pagerId, {edit: true, add: true, del: true},
            {}, {
                //添加后
                closeAfterAdd: true,//关闭添加框
                afterSubmit: function (data) { //提交之后执行的方法
                    //文件的上传
                    $.ajaxFileUpload({
                        url: "${path}/chapter/uploadChapter",
                        type: "post",
                        datatype: "json",
                        data: {id: data.responseText},  //获取id
                        fileElementId: "src",  //需要上传的文件域的ID
                        success: function () {
                            //
                            $("#" + subgridTableId).trigger("reloadGrid");
                        }
                    });
                    return "he";   //必须有返回值  返回值随便写
                }
            }
        );
    }

    //下载
    function audioDownload(audioName) {

        location.href = "${path}/chapter/audioDownload?audioName=" + audioName;

    }

    //在线播放
    function audioPlayer(audioName) {
        //展示模态框
        $("#AudioModal").modal("show");
        //给音频标签设置值
        $("#myAudio").attr("src", "${path}/upload/photo/" + audioName);
    }
</script>


<%--初始化面板--%>
<div class="panel panel-warning">

    <%--面板标题--%>
    <div class="panel panel-heading">
        <h3>专辑管理</h3>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a>专辑管理</a></li>
    </ul>

    <%--初始化表单--%>
    <table id="abTable"/>

    <%--分页工具栏--%>
    <div id="abPage"/>

    <%--播放的模态框--%>
    <div id="AudioModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">

            <audio id="myAudio" src="" controls/>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>