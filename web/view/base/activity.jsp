<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/11/18
  Time: 14:58
  活动页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .activity-top {
        height: 80px;
        display: block;
        background-color: #cd0013;
    }

    .activity-top > div {
        background-repeat: no-repeat;
        background-position: 50% 0;
        position: relative;
        height: 100%;
        background-image: url("upload/sys_images/1.jpg");
        margin-left: auto;
        margin-right: auto;
    }

    .activity-top-head {
        width: 190px;
        height: 80px;
        background-image: url("upload/sys_images/500a04376c22010b.jpg");
    }

    .activity-top-close {
        position: absolute;
        top: 5px;
        right: 0;
        cursor: pointer;
        font-weight: 700;
        font-size: 14px;
        color: #fff;
        background: #2d2d2d;
        width: 20px;
        height: 20px;
        text-align: center;
        line-height: 20px;
        opacity: .3;
    }

    .activity-top-item{
        width: 116px;
        height: 46px;
        margin-right: 10px;
        display: inline-block;
        position: absolute;
        top: 16px;
        right: 20px;

    }
    .activity-top-item img{
        text-align: right;
        height: 100%;
        line-height: 80px;
        padding-right: 20px;
    }
</style>

<div class="activity-top">
    <div class="w">
        <div class="activity-top-head">

        </div>

        <i class="activity-top-close glyphicon glyphicon-remove"></i>

        <div class="activity-top-item">
            <img src="upload/sys_images/65f4b27137d51f6d.png" alt="">
        </div>
    </div>
</div>
