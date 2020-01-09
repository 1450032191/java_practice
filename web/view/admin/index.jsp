<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName","首页");
%>
<jsp:include page="base/head.jsp"></jsp:include>
<div style="height: 125px;" class="row">
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-primary card-round">
            <div class="card-body">
                <div class="row">
                    <div class="col-5">
                        <div class="icon-big text-center">
                            <i class="flaticon-users"></i>
                        </div>
                    </div>
                    <div class="col col-stats">
                        <div class="numbers">
                            <p class="card-category">新用户</p>
                            <h4 class="card-title" id="today_user_count">1,294</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-info card-round">
            <div class="card-body">
                <div class="row">
                    <div class="col-5">
                        <div class="icon-big text-center">
                            <i class="flaticon-interface-6"></i>
                        </div>
                    </div>
                    <div class="col col-stats">
                        <div class="numbers">
                            <p class="card-category">新商品</p>
                            <h4 class="card-title" id="today_commodity_count">1303</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-success card-round">
            <div class="card-body ">
                <div class="row">
                    <div class="col-5">
                        <div class="icon-big text-center">
                            <i class="flaticon-analytics"></i>
                        </div>
                    </div>
                    <div class="col col-stats">
                        <div class="numbers">
                            <p class="card-category">今日订单总额</p>
                            <h4 class="card-title" id="today_order_price">$ 1,345</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-secondary card-round">
            <div class="card-body ">
                <div class="row">
                    <div class="col-5">
                        <div class="icon-big text-center">
                            <i class="flaticon-success"></i>
                        </div>
                    </div>
                    <div class="col col-stats">
                        <div class="numbers">
                            <p class="card-category">今日订单数</p>
                            <h4 class="card-title" id="today_order_count">576</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-round">
            <div class="card-body ">
                <div class="row align-items-center">
                    <div class="col-icon">
                        <div class="icon-big text-center icon-primary bubble-shadow-small">
                            <i class="fas fa-users"></i>
                        </div>
                    </div>
                    <div class="col col-stats ml-3 ml-sm-0">
                        <div class="numbers">
                            <p class="card-category">用户总数</p>
                            <h4 class="card-title" id="user_count">1,294</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-round">
            <div class="card-body">
                <div class="row align-items-center">
                    <div class="col-icon">
                        <div class="icon-big text-center icon-info bubble-shadow-small">
                            <i class="far fa-newspaper"></i>
                        </div>
                    </div>
                    <div class="col col-stats ml-3 ml-sm-0">
                        <div class="numbers">
                            <p class="card-category">商品数量</p>
                            <h4 class="card-title" id="commodit_count">1303</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-round">
            <div class="card-body">
                <div class="row align-items-center">
                    <div class="col-icon">
                        <div class="icon-big text-center icon-success bubble-shadow-small">
                            <i class="far fa-chart-bar"></i>
                        </div>
                    </div>
                    <div class="col col-stats ml-3 ml-sm-0">
                        <div class="numbers">
                            <p class="card-category">订单总金额</p>
                            <h4 class="card-title" id="order_price">$ 1,345</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <div class="card card-stats card-round">
            <div class="card-body">
                <div class="row align-items-center">
                    <div class="col-icon">
                        <div class="icon-big text-center icon-secondary bubble-shadow-small">
                            <i class="far fa-check-circle"></i>
                        </div>
                    </div>
                    <div class="col col-stats ml-3 ml-sm-0">
                        <div class="numbers">
                            <p class="card-category">订单总数</p>
                            <h4 class="card-title" id="order_count">576</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-md-8">
        <div class="card">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">近七日的订单详情</div>
                    <div class="card-tools">
                        <a href="#" class="btn btn-info btn-border btn-round btn-sm mr-2">
												<span class="btn-label">
													<i class="fa fa-pencil"></i>
												</span>
                            Export
                        </a>
                        <a href="#" class="btn btn-info btn-border btn-round btn-sm">
												<span class="btn-label">
													<i class="fa fa-print"></i>
												</span>
                            Print
                        </a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="chart-container" style="min-height: 375px">
                    <canvas id="statisticsChart"></canvas>
                </div>
                <div id="myChartLegend"></div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card card-secondary">
            <div class="card-header">
                <div class="card-title">年收入</div>
                <div class="card-category">2020.01 - 2021.01</div>
            </div>
            <div class="card-body pb-0">
                <div class="mb-4 mt-2">
                    <h1>￥ <span id="year_order_price"></span></h1>
                </div>
                <div class="pull-in">
                    <canvas id="dailySalesChart"></canvas>
                </div>
            </div>
        </div>
        <div class="card card-info bg-info-gradient">
            <div class="card-body">
                <h4 class="mb-1 fw-bold">未完成订单占比</h4>
                <div id="task-complete" class="chart-circle mt-4 mb-3"></div>
            </div>
        </div>
    </div>
</div>

<!-- Chart JS -->
<script src="static/admin/assets/js/plugin/chart.js/chart.min.js"></script>
<!-- Chart Circle -->
<script src="static/admin/assets/js/plugin/chart-circle/circles.min.js"></script>
<script>

    $(function () {
        $.get("bao/get.do",function (result) {
            if(result.code){
                var data = result.data.data;
                $("#today_user_count").text(data.today_user_count);
                $("#today_commodity_count").text(data.today_commodity_count);
                $("#today_order_price").text(data.today_order_price);
                $("#today_order_count").text(data.today_order_count);
                $("#user_count").text(data.user_count);
                $("#commodit_count").text(data.commodit_count);
                $("#order_price").text(data.order_price);
                $("#order_count").text(data.order_count);
                $("#year_order_price").text(data.year_order_price);

                var zanbi = data.zanbi;
                var ctx = document.getElementById('statisticsChart').getContext('2d');
                var statisticsChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels:result.data.dates,
                        datasets: [ {
                            label: "订单量",
                            borderColor: '#f3545d',
                            pointBackgroundColor: 'rgba(243, 84, 93, 0.2)',
                            pointRadius: 0,
                            backgroundColor: 'rgba(243, 84, 93, 0.1)',
                            legendColor: '#f3545d',
                            fill: true,
                            borderWidth: 2,
                            data: result.data.orderCounts
                        }, {
                            label: "订单总额",
                            borderColor: '#fdaf4b',
                            pointBackgroundColor: 'rgba(253, 175, 75, 0.2)',
                            pointRadius: 0,
                            backgroundColor: 'rgba(253, 175, 75, 0.1)',
                            legendColor: '#fdaf4b',
                            fill: true,
                            borderWidth: 2,
                            data: result.data.orderPrices
                        }, {
                            label: "新用户增长",
                            borderColor: '#177dff',
                            pointBackgroundColor: 'rgba(23, 125, 255, 0.2)',
                            pointRadius: 0,
                            backgroundColor: 'rgba(23, 125, 255, 0.1)',
                            legendColor: '#177dff',
                            fill: true,
                            borderWidth: 2,
                            data: result.data.newUser
                        }]
                    },
                    options : {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            display: false
                        },
                        tooltips: {
                            bodySpacing: 4,
                            mode:"nearest",
                            intersect: 0,
                            position:"nearest",
                            xPadding:10,
                            yPadding:10,
                            caretPadding:10
                        },
                        layout:{
                            padding:{left:15,right:15,top:15,bottom:15}
                        },
                        scales: {
                            yAxes: [{
                                ticks: {
                                    fontColor: "rgba(0,0,0,0.5)",
                                    fontStyle: "500",
                                    beginAtZero: false,
                                    maxTicksLimit: 5,
                                    padding: 20
                                },
                                gridLines: {
                                    drawTicks: false,
                                    display: false
                                }
                            }],
                            xAxes: [{
                                gridLines: {
                                    zeroLineColor: "transparent"
                                },
                                ticks: {
                                    padding: 20,
                                    fontColor: "rgba(0,0,0,0.5)",
                                    fontStyle: "500"
                                }
                            }]
                        },
                        legendCallback: function(chart) {
                            var text = [];
                            text.push('<ul class="' + chart.id + '-legend html-legend">');
                            for (var i = 0; i < chart.data.datasets.length; i++) {
                                text.push('<li><span style="background-color:' + chart.data.datasets[i].legendColor + '"></span>');
                                if (chart.data.datasets[i].label) {
                                    text.push(chart.data.datasets[i].label);
                                }
                                text.push('</li>');
                            }
                            text.push('</ul>');
                            return text.join('');
                        }
                    }
                });

                var dailySalesChart = document.getElementById('dailySalesChart').getContext('2d');
                var myDailySalesChart = new Chart(dailySalesChart, {
                    type: 'line',
                    data: {
                        labels:["January",
                            "February",
                            "March",
                            "April",
                            "May",
                            "June",
                            "July",
                            "August",
                            "September"],
                        datasets:[ {
                            label: "Sales Analytics", fill: !0, backgroundColor: "rgba(255,255,255,0.2)", borderColor: "#fff", borderCapStyle: "butt", borderDash: [], borderDashOffset: 0, pointBorderColor: "#fff", pointBackgroundColor: "#fff", pointBorderWidth: 1, pointHoverRadius: 5, pointHoverBackgroundColor: "#fff", pointHoverBorderColor: "#fff", pointHoverBorderWidth: 1, pointRadius: 1, pointHitRadius: 5, data: [65, 59, 80, 81, 56, 55, 40, 35, 30]
                        }]
                    },
                    options : {
                        maintainAspectRatio:!1, legend: {
                            display: !1
                        }
                        , animation: {
                            easing: "easeInOutBack"
                        }
                        , scales: {
                            yAxes:[ {
                                display:!1, ticks: {
                                    fontColor: "rgba(0,0,0,0.5)", fontStyle: "bold", beginAtZero: !0, maxTicksLimit: 10, padding: 0
                                }
                                , gridLines: {
                                    drawTicks: !1, display: !1
                                }
                            }
                            ], xAxes:[ {
                                display:!1, gridLines: {
                                    zeroLineColor: "transparent"
                                }
                                , ticks: {
                                    padding: -20, fontColor: "rgba(255,255,255,0.2)", fontStyle: "bold"
                                }
                            }
                            ]
                        }
                    }
                });

                Circles.create({
                    id:           'task-complete',
                    radius:       50,
                    value:        zanbi,
                    maxValue:     100,
                    width:        5,
                    text:         function(value){return value + '%';},
                    colors:       ['#36a3f7', '#fff'],
                    duration:     400,
                    wrpClass:     'circles-wrp',
                    textClass:    'circles-text',
                    styleWrapper: true,
                    styleText:    true
                })

            }else {
                var ctx = document.getElementById('statisticsChart').getContext('2d');
                var statisticsChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                        datasets: [ {
                            label: "Subscribers",
                            borderColor: '#f3545d',
                            pointBackgroundColor: 'rgba(243, 84, 93, 0.2)',
                            pointRadius: 0,
                            backgroundColor: 'rgba(243, 84, 93, 0.1)',
                            legendColor: '#f3545d',
                            fill: true,
                            borderWidth: 2,
                            data: [154, 184, 175, 203, 210, 231, 240, 278, 252, 312, 320, 374]
                        }, {
                            label: "New Visitors",
                            borderColor: '#fdaf4b',
                            pointBackgroundColor: 'rgba(253, 175, 75, 0.2)',
                            pointRadius: 0,
                            backgroundColor: 'rgba(253, 175, 75, 0.1)',
                            legendColor: '#fdaf4b',
                            fill: true,
                            borderWidth: 2,
                            data: [256, 230, 245, 287, 240, 250, 230, 295, 331, 431, 456, 521]
                        }, {
                            label: "Active Users",
                            borderColor: '#177dff',
                            pointBackgroundColor: 'rgba(23, 125, 255, 0.2)',
                            pointRadius: 0,
                            backgroundColor: 'rgba(23, 125, 255, 0.1)',
                            legendColor: '#177dff',
                            fill: true,
                            borderWidth: 2,
                            data: [542, 480, 430, 550, 530, 453, 380, 434, 568, 610, 700, 900]
                        }]
                    },
                    options : {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            display: false
                        },
                        tooltips: {
                            bodySpacing: 4,
                            mode:"nearest",
                            intersect: 0,
                            position:"nearest",
                            xPadding:10,
                            yPadding:10,
                            caretPadding:10
                        },
                        layout:{
                            padding:{left:15,right:15,top:15,bottom:15}
                        },
                        scales: {
                            yAxes: [{
                                ticks: {
                                    fontColor: "rgba(0,0,0,0.5)",
                                    fontStyle: "500",
                                    beginAtZero: false,
                                    maxTicksLimit: 5,
                                    padding: 20
                                },
                                gridLines: {
                                    drawTicks: false,
                                    display: false
                                }
                            }],
                            xAxes: [{
                                gridLines: {
                                    zeroLineColor: "transparent"
                                },
                                ticks: {
                                    padding: 20,
                                    fontColor: "rgba(0,0,0,0.5)",
                                    fontStyle: "500"
                                }
                            }]
                        },
                        legendCallback: function(chart) {
                            var text = [];
                            text.push('<ul class="' + chart.id + '-legend html-legend">');
                            for (var i = 0; i < chart.data.datasets.length; i++) {
                                text.push('<li><span style="background-color:' + chart.data.datasets[i].legendColor + '"></span>');
                                if (chart.data.datasets[i].label) {
                                    text.push(chart.data.datasets[i].label);
                                }
                                text.push('</li>');
                            }
                            text.push('</ul>');
                            return text.join('');
                        }
                    }
                });

                var dailySalesChart = document.getElementById('dailySalesChart').getContext('2d');
                var myDailySalesChart = new Chart(dailySalesChart, {
                    type: 'line',
                    data: {
                        labels:["January",
                            "February",
                            "March",
                            "April",
                            "May",
                            "June",
                            "July",
                            "August",
                            "September"],
                        datasets:[ {
                            label: "Sales Analytics", fill: !0, backgroundColor: "rgba(255,255,255,0.2)", borderColor: "#fff", borderCapStyle: "butt", borderDash: [], borderDashOffset: 0, pointBorderColor: "#fff", pointBackgroundColor: "#fff", pointBorderWidth: 1, pointHoverRadius: 5, pointHoverBackgroundColor: "#fff", pointHoverBorderColor: "#fff", pointHoverBorderWidth: 1, pointRadius: 1, pointHitRadius: 5, data: [65, 59, 80, 81, 56, 55, 40, 35, 30]
                        }]
                    },
                    options : {
                        maintainAspectRatio:!1, legend: {
                            display: !1
                        }
                        , animation: {
                            easing: "easeInOutBack"
                        }
                        , scales: {
                            yAxes:[ {
                                display:!1, ticks: {
                                    fontColor: "rgba(0,0,0,0.5)", fontStyle: "bold", beginAtZero: !0, maxTicksLimit: 10, padding: 0
                                }
                                , gridLines: {
                                    drawTicks: !1, display: !1
                                }
                            }
                            ], xAxes:[ {
                                display:!1, gridLines: {
                                    zeroLineColor: "transparent"
                                }
                                , ticks: {
                                    padding: -20, fontColor: "rgba(255,255,255,0.2)", fontStyle: "bold"
                                }
                            }
                            ]
                        }
                    }
                });

                Circles.create({
                    id:           'task-complete',
                    radius:       50,
                    value:        89,
                    maxValue:     100,
                    width:        5,
                    text:         function(value){return value + '%';},
                    colors:       ['#36a3f7', '#fff'],
                    duration:     400,
                    wrpClass:     'circles-wrp',
                    textClass:    'circles-text',
                    styleWrapper: true,
                    styleText:    true
                })
            }
        });
    })

</script>


<jsp:include page="base/foot.jsp"></jsp:include>