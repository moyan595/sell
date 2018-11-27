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
                    <h4 class="page-title">类目管理</h4>
                    <div class="d-flex align-items-center"></div>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex no-block justify-content-end align-items-center">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">首页</li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    <a href="${basePath}/seller/category/list">类目列表</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    类目修改
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
                            <h4 class="card-title">修改类目</h4>
                            <form class="m-t-40" novalidate action="${basePath}/seller/category/save" method="post">
                                <input type="hidden" name="categoryId" value="${(productCategory.categoryId)!''}">
                                <div class="form-group">
                                    <h5>名称 <span class="text-danger">*</span></h5>
                                    <div class="controls">
                                        <input type="text" name="categoryName" value="${(productCategory.categoryName)!''}" class="form-control" required data-validation-required-message="类目名称必填">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <h5>类型 <span class="text-danger">*</span></h5>
                                    <div class="controls">
                                        <#if productCategory??>
                                            <input type="text" name="categoryType" value="${productCategory.categoryType}"
                                                   class="form-control" required data-validation-required-message="类目类型必填"
                                                   readonly>
                                        <#else>
                                            <input type="text" name="categoryType"
                                                   class="form-control" required data-validation-required-message="类目类型必填">
                                        </#if>

                                    </div>
                                </div>
                                <div class="text-xs-right">
                                    <button type="submit" class="btn btn-info">提交表单</button>
                                    <button type="reset" class="btn btn-inverse">重置表单</button>
                                </div>
                            </form>
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

<script src="${basePath}/assets/extra-libs/jqbootstrapvalidation/validation.js"></script>
<script>
    ! function(window, document, $) {
        "use strict";
        $("input,select,textarea").not("[type=submit]").jqBootstrapValidation();
    }(window, document, jQuery);
</script>
</body>

</html>