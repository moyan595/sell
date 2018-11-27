<!DOCTYPE html>
<html dir="ltr" lang="zh">
<!-- head -->
<#include "../common/header.ftl">
<body>
<div id="main-wrapper">
    <#-- Topbar -->
    <#include "../common/topbar.ftl">

    <#-- Left Sidebar -->
    <#include "../common/sidebar.ftl">

    <#-- Page wrapper  -->
    <div class="page-wrapper">
        <#-- 页面功能导航 -->
        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title">订单管理</h4>
                    <div class="d-flex align-items-center"></div>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex no-block justify-content-end align-items-center">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">首页</li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    订单
                                </li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

        <#-- 页面主体内容 -->
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">订单列表</h4>
                            <div class="table-responsive">
                                <table id="zero_config" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th class="text-center">订单ID</th>
                                        <th class="text-center">姓名</th>
                                        <th class="text-center">手机号</th>
                                        <th class="text-center">地址</th>
                                        <th class="text-center">金额</th>
                                        <th class="text-center">订单状态</th>
                                        <th class="text-center">支付状态</th>
                                        <th class="text-center">创建时间</th>
                                        <th class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list orderDTOPage.content as orderDTO >
                                    <tr>
                                        <td>${orderDTO.orderId}</td>
                                        <td>${orderDTO.buyerName}</td>
                                        <td>${orderDTO.buyerPhone}</td>
                                        <td>${orderDTO.buyerAddress}</td>
                                        <td>${orderDTO.orderAmount}</td>
                                        <td>${orderDTO.orderStatusMessage}</td>
                                        <td>${orderDTO.payStatusMessage}</td>
                                        <td>${orderDTO.createTime}</td>
                                        <td>
                                            <a class="btn btn-sm btn-outline-primary" href="${basePath}/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                            <#if orderDTO.orderStatus == 0>
                                            <a class="btn btn-sm btn-outline-danger" href="${basePath}/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                            </#if>
                                        </td>
                                    </tr>
                                    </#list>
                                    </tbody>
                                </table>
                                <#-- 分页 -->
                                <ul class="pagination float-right">
                                    <#if orderDTOPage.first>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="${basePath}/seller/order/list?page=${orderDTOPage.number}">
                                            上一页
                                        </a>
                                    </li>
                                    <#else>
                                    <li class="page-item">
                                        <a class="page-link" href="${basePath}/seller/order/list?page=${orderDTOPage.number}" aria-label="Previous">
                                            上一页
                                        </a>
                                    </li>
                                    </#if>
                                    <#list 1..orderDTOPage.totalPages as index>
                                        <#if orderDTOPage.number == (index - 1)>
                                    <li class="page-item active">
                                        <a class="page-link" href="${basePath}/seller/order/list?page=${index}">${index}</a>
                                    </li>
                                        <#else>
                                    <li class="page-item">
                                        <a class="page-link" href="${basePath}/seller/order/list?page=${index}">${index}</a>
                                    </li>
                                        </#if>
                                    </#list>
                                    <#if orderDTOPage.last>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="${basePath}/seller/order/list?page=${orderDTOPage.number+1}" aria-label="Next">
                                            下一页
                                        </a>
                                    </li>
                                    <#else>
                                    <li class="page-item">
                                        <a class="page-link" href="${basePath}/seller/order/list?page=${orderDTOPage.number+2}" aria-label="Next">
                                            下一页
                                        </a>
                                    </li>
                                    </#if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#-- 消息弹窗 -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">系统消息</h4>
                <button onclick="closeMusic()" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <h4>您有了新的订单，请及时处理！</h4>
            </div>
            <div class="modal-footer">
                <button onclick="closeMusic()" type="button" class="btn btn-danger waves-effect" data-dismiss="modal"> 关闭 </button>
            </div>
        </div>
    </div>
</div>

<#-- 播放音乐 -->
<audio id="notice" loop>
    <source src="${basePath}/mp3/01.mp3" type="audio/mpeg" />
</audio>

<#-- customizer Panel -->
<#include "../common/layout.ftl">

<#-- All Jquery -->
<#include "../common/js.ftl">

<script>
    //HTML5 WebSocket API
    //创建 WebSocket 对象
    var websocket = null;
    if ('WebSocket' in window){
        websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
    } else {
        alert('您的浏览器不支持WebSocket！推荐使用谷歌浏览器！')
    }

    //建立连接
    websocket.onopen = function (ev) {
        console.log('建立连接');
    }

    //关闭连接
    websocket.onclose = function (ev) {
        console.log('连接关闭');
    }

    //接收消息
    websocket.onmessage = function (ev) {
        console.log('收到消息：' + ev.data);
        //弹窗提醒
        $('#myModal').modal('show');
        //播放音乐
        $('#notice')[0].play();
    }

    //错误处理
    websocket.onerror = function (ev) {
        alert('WebSocket通信发生错误！');
    }
    
    websocket.onbeforeunload = function () {
        websocket.close();
    }

    function closeMusic() {
        //暂停音乐
        $('#notice')[0].pause();
        //页面刷新
        window.location.reload();
    }
</script>
</body>
</html>