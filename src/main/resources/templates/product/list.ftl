<!DOCTYPE html>
<html dir="ltr" lang="zh">
<!-- head -->
<#include "../common/header.ftl">
<body>
<div id="main-wrapper">
    <!-- Topbar header - style you can find in pages.scss -->
    <#include "../common/topbar.ftl">

    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <#include "../common/sidebar.ftl">

    <!-- Page wrapper  -->
    <div class="page-wrapper">

        <!-- 页面功能导航 -->
        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title">商品管理</h4>
                    <div class="d-flex align-items-center"></div>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex no-block justify-content-end align-items-center">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">首页</li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    商品管理
                                </li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

        <!-- 页面主体信息 -->
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">商品列表</h4>
                            <div class="table-responsive">
                                <table id="zero_config" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th class="text-center">商品ID</th>
                                        <th class="text-center">名称</th>
                                        <th class="text-center">图片</th>
                                        <th class="text-center">单价</th>
                                        <th class="text-center">库存</th>
                                        <th class="text-center">类目</th>
                                        <th class="text-center">创建时间</th>
                                        <th class="text-center">修改时间</th>
                                        <th class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list productInfoPage.content as productInfo >
                                    <tr>
                                        <td>${productInfo.productId}</td>
                                        <td>${productInfo.productName}</td>
                                        <td>
                                            <img src="${productInfo.productIcon}" width="60" height="60" />
                                        </td>
                                        <td>${productInfo.productPrice}</td>
                                        <td>${productInfo.productStock}</td>
                                        <td>${productInfo.categoryType}</td>
                                        <td>${productInfo.createTime}</td>
                                        <td>${productInfo.updateTime}</td>
                                        <td>
                                            <a class="btn btn-sm btn-outline-primary" href="${basePath}/seller/product/index?productId=${productInfo.productId}">修改</a>
                                            <#if productInfo.productStatus == 0>
                                            <a class="btn btn-sm btn-outline-danger" href="${basePath}/seller/product/offSale?productId=${productInfo.productId}">下架</a>
                                            <#else>
                                            <a class="btn btn-sm btn-outline-danger" href="${basePath}/seller/product/onSale?productId=${productInfo.productId}">上架</a>
                                            </#if>
                                        </td>
                                    </tr>
                                    </#list>
                                    </tbody>
                                </table>
                                <!-- 分页 -->
                                <ul class="pagination float-right">
                                    <#if productInfoPage.first>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="${basePath}/seller/product/list?page=${productInfoPage.number}">
                                            上一页
                                        </a>
                                    </li>
                                    <#else>
                                    <li class="page-item">
                                        <a class="page-link" href="${basePath}/seller/product/list?page=${productInfoPage.number}" aria-label="Previous">
                                            上一页
                                        </a>
                                    </li>
                                    </#if>
                                    <#list 1..productInfoPage.totalPages as index>
                                        <#if productInfoPage.number == (index - 1)>
                                    <li class="page-item active">
                                        <a class="page-link" href="${basePath}/seller/product/list?page=${index}">${index}</a>
                                    </li>
                                        <#else>
                                    <li class="page-item">
                                        <a class="page-link" href="${basePath}/seller/product/list?page=${index}">${index}</a>
                                    </li>
                                        </#if>
                                    </#list>
                                    <#if productInfoPage.last>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="${basePath}/seller/product/list?page=${productInfoPage.number+1}" aria-label="Next">
                                            下一页
                                        </a>
                                    </li>
                                    <#else>
                                    <li class="page-item">
                                        <a class="page-link" href="${basePath}/seller/product/list?page=${productInfoPage.number+2}" aria-label="Next">
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

<!-- customizer Panel -->
<#include "../common/layout.ftl">

<!-- All Jquery -->
<#include "../common/js.ftl">

</body>

</html>