<%--
  Created by IntelliJ IDEA.
  User: 60181
  Date: 2020/1/7
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商场相关图表</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="../../css/Chart.js"></script>
    <script src="../../css/utils.js"></script>
    <style>
        tr td {
            text-align: center;
            align-self: center;
            align-content: center;
        }
    </style>
</head>
<body>
<table border="0px">
    <tr>
        <td width="1000px" height="480px">
            <div>
                <iframe src="gridlines-display.html" frameborder="0" width="900px" height="470px" scrolling="0">
                </iframe>
            </div>
        </td>
        <td width="1000px" height="480px">
            <iframe src="combo-bar-line.html" frameborder="0" width="900px" height="470px" scrolling="0">
            </iframe>
        </td>
    </tr>
    <tr>
        <td width="1000px" height="480px">
            <iframe src="pie.html" frameborder="0" width="900px" height="470px" scrolling="0">
            </iframe>
        </td>
        <td width="1000px" height="480px">
            <iframe src="point-styles.html" frameborder="0" width="900px" height="470px" scrolling="0">
            </iframe>
        </td>
    </tr>
</table>
</body>
</html>
