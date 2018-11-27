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
                                    <a href="${basePath}/seller/product/list">商品列表</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    商品修改
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
                            <h4 class="card-title">修改商品</h4>
                            <form class="m-t-40" novalidate action="${basePath}/seller/product/save" method="post">
                                <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
                                <div class="form-group">
                                    <h5>名称 <span class="text-danger">*</span></h5>
                                    <div class="controls">
                                        <input type="text" name="productName" value="${(productInfo.productName)!''}" class="form-control" required data-validation-required-message="商品名称必填">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <h5>价格 <span class="text-danger">*</span></h5>
                                    <div class="input-group">
                                        <div class="input-group-append">
                                            <span class="input-group-text"><i class="fas fa-dollar-sign"></i></span>
                                        </div>
                                        <input type="number" step="0.01" name="productPrice" value="${(productInfo.productPrice)!''}" class="form-control" required data-validation-required-message="商品价格必填">
                                        <div class="input-group-append">
                                            <span class="input-group-text">0.00</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <h5>库存 <span class="text-danger">*</span></h5>
                                    <div class="input-group">
                                        <input type="number" name="productStock" value="${(productInfo.productStock)!''}" class="form-control"
                                               required data-validation-containsnumber-regex="(\d)+" data-validation-required-message="商品库存必填">
                                        <div class="input-group-append">
                                            <span class="input-group-text">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <h5>描述 <span class="text-danger">*</span></h5>
                                    <div class="controls">
                                        <input type="text" name="productDescription" value="${(productInfo.productDescription)!''}" class="form-control" required data-validation-required-message="商品描述必填">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <h5>图片 <span class="text-danger">*</span></h5>
                                    <div class="controls">
                                        <#if productInfo??>
                                            <img src="${productInfo.productIcon}" width="100" height="100" /><br><br>
                                        </#if>
                                        <input type="text" name="productIcon" value="${(productInfo.productIcon)!''}" class="form-control"
                                               placeholder="图片URL" required data-validation-required-message="商品图片必填">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <h5>类目 <span class="text-danger">*</span></h5>
                                    <div class="controls">
                                        <select name="categoryType" class="form-control" required data-validation-required-message="商品类目必选">
                                            <option value="">选择商品类目</option>
                                            <#list productCategoryList as productCategory>
                                            <#if productInfo?? && productInfo.categoryType == productCategory.categoryType>
                                                <option value="${productCategory.categoryType}" selected>${productCategory.categoryName}</option>
                                            <#else>
                                                <option value="${productCategory.categoryType}">${productCategory.categoryName}</option>
                                            </#if>
                                            </#list>
                                        </select>
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