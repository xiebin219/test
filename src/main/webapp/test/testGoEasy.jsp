<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%--<script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>--%>
<script type="text/javascript" src="${path}/js/goEasy.js"></script>

<script>
    /*初始化GoEasy对象*/
    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-df0a45499f274b2bae29ae50a6a12dc9", //替换为您的应用appkey
    });

    /*接收消息*/
    goEasy.subscribe({
        channel: "cmfz", //替换为您自己的channel
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });
</script>
