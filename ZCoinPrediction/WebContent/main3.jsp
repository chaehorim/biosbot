<!DOCTYPE html>
<!--
* CoreUI - Free Bootstrap Admin Template
* @version v2.0.0-beta.0
* @link https://coreui.io
* Copyright (c) 2018 creativeLabs Łukasz Holeczek
* Licensed under MIT (https://coreui.io/license)
-->

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
    <meta name="author" content="Łukasz Holeczek">
    <meta name="keyword" content="Bootstrap,Admin,Template,Open,Source,jQuery,CSS,HTML,RWD,Dashboard">
    <title>CoreUI Free Bootstrap Admin Template</title>
    <!-- Icons-->
    <link href="vendors/css/flag-icon.min.css" rel="stylesheet">
    <link href="vendors/css/font-awesome.min.css" rel="stylesheet">
    <link href="vendors/css/simple-line-icons.css" rel="stylesheet">
    <!-- Main styles for this application-->
    <link href="css/style.css" rel="stylesheet">
    <link href="vendors/css/pace.min.css" rel="stylesheet">
  </head>
  <body class="app header-fixed sidebar-fixed aside-menu-fixed sidebar-lg-show">
    <jsp:include page="top.jsp" flush="true"/>
    <div class="app-body">
      <jsp:include page="menu.jsp" flush="true"/>
		<main class="main">
        <!-- Breadcrumb-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">Home</li>
          <li class="breadcrumb-item">
            <a href="#">Admin</a>
          </li>
          <li class="breadcrumb-item active">Dashboard</li>
          <!-- Breadcrumb Menu-->
          <li class="breadcrumb-menu d-md-down-none">
            <div class="btn-group" role="group" aria-label="Button group">
              <a class="btn" href="#">
                <i class="icon-speech"></i>
              </a>
              <a class="btn" href="./">
                <i class="icon-graph"></i>  Dashboard</a>
              <a class="btn" href="#">
                <i class="icon-settings"></i>  Settings</a>
            </div>
          </li>
        </ol>
        <div class="container-fluid">
          <div class="animated fadeIn">
            <div class="row">
              <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-primary">
                  <div class="card-body pb-0">
                    <div class="btn-group float-right">
                      <button type="button" class="btn btn-transparent dropdown-toggle p-0" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="icon-settings"></i>
                      </button>
                      <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                      </div>
                    </div>
                    <div class="text-value">9.827</div>
                    <div>Members online</div>
                  </div>
                  <div class="chart-wrapper mt-3 px-3" style="height:70px;">
                    <canvas id="card-chart1" class="chart" height="70"></canvas>
                  </div>
                </div>
              </div>
              <!--/.col-->
              <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-info">
                  <div class="card-body pb-0">
                    <button type="button" class="btn btn-transparent p-0 float-right">
                      <i class="icon-location-pin"></i>
                    </button>
                    <div class="text-value">9.823</div>
                    <div>Members online</div>
                  </div>
                  <div class="chart-wrapper mt-3 px-3" style="height:70px;">
                    <canvas id="card-chart2" class="chart" height="70"></canvas>
                  </div>
                </div>
              </div>
              <!--/.col-->
              <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-warning">
                  <div class="card-body pb-0">
                    <div class="btn-group float-right">
                      <button type="button" class="btn btn-transparent dropdown-toggle p-0" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="icon-settings"></i>
                      </button>
                      <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                      </div>
                    </div>
                    <div class="text-value">9.823</div>
                    <div>Members online</div>
                  </div>
                  <div class="chart-wrapper mt-3" style="height:70px;">
                    <canvas id="card-chart3" class="chart" height="70"></canvas>
                  </div>
                </div>
              </div>
              <!--/.col-->
              <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-danger">
                  <div class="card-body pb-0">
                    <div class="btn-group float-right">
                      <button type="button" class="btn btn-transparent dropdown-toggle p-0" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="icon-settings"></i>
                      </button>
                      <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                      </div>
                    </div>
                    <div class="text-value">9.823</div>
                    <div>Members online</div>
                  </div>
                  <div class="chart-wrapper mt-3 px-3" style="height:70px;">
                    <canvas id="card-chart4" class="chart" height="70"></canvas>
                  </div>
                </div>
              </div>
              <!--/.col-->
            </div>
            <!--/.row-->

            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-5">
                    <h4 class="card-title mb-0">Traffic</h4>
                    <div class="small text-muted">November 2017</div>
                  </div>
                  <!--/.col-->
                  <div class="col-sm-7 d-none d-md-block">
                    <button type="button" class="btn btn-primary float-right">
                      <i class="icon-cloud-download"></i>
                    </button>
                    <div class="btn-group btn-group-toggle float-right mr-3" data-toggle="buttons">
                      <label class="btn btn-outline-secondary">
                        <input type="radio" name="options" id="option1" autocomplete="off"> Day
                      </label>
                      <label class="btn btn-outline-secondary active">
                        <input type="radio" name="options" id="option2" autocomplete="off" checked> Month
                      </label>
                      <label class="btn btn-outline-secondary">
                        <input type="radio" name="options" id="option3" autocomplete="off"> Year
                      </label>
                    </div>
                  </div>
                  <!--/.col-->
                </div>
                <!--/.row-->
                <div class="chart-wrapper" style="height:300px;margin-top:40px;">
                  <canvas id="main-chart" class="chart" height="300"></canvas>
                </div>
              </div>
              <div class="card-footer">
                <div class="row text-center">
                  <div class="col-sm-12 col-md mb-sm-2 mb-0">
                    <div class="text-muted">Visits</div>
                    <strong>29.703 Users (40%)</strong>
                    <div class="progress progress-xs mt-2">
                      <div class="progress-bar bg-success" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md mb-sm-2 mb-0">
                    <div class="text-muted">Unique</div>
                    <strong>24.093 Users (20%)</strong>
                    <div class="progress progress-xs mt-2">
                      <div class="progress-bar bg-info" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md mb-sm-2 mb-0">
                    <div class="text-muted">Pageviews</div>
                    <strong>78.706 Views (60%)</strong>
                    <div class="progress progress-xs mt-2">
                      <div class="progress-bar bg-warning" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md mb-sm-2 mb-0">
                    <div class="text-muted">New Users</div>
                    <strong>22.123 Users (80%)</strong>
                    <div class="progress progress-xs mt-2">
                      <div class="progress-bar bg-danger" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md mb-sm-2 mb-0">
                    <div class="text-muted">Bounce Rate</div>
                    <strong>40.15%</strong>
                    <div class="progress progress-xs mt-2">
                      <div class="progress-bar" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--/.card-->

            <div class="row">
              <div class="col-sm-6 col-lg-3">
                <div class="brand-card">
                  <div class="brand-card-header bg-facebook">
                    <i class="fa fa-facebook"></i>
                    <div class="chart-wrapper">
                      <canvas id="social-box-chart-1" height="90"></canvas>
                    </div>
                  </div>
                  <div class="brand-card-body">
                    <div>
                      <div class="text-value">89k</div>
                      <div class="text-uppercase text-muted small">friends</div>
                    </div>
                    <div>
                      <div class="text-value">459</div>
                      <div class="text-uppercase text-muted small">feeds</div>
                    </div>
                  </div>
                </div>
              </div>
              <!--/.col-->
              <div class="col-sm-6 col-lg-3">
                <div class="brand-card">
                  <div class="brand-card-header bg-twitter">
                    <i class="fa fa-twitter"></i>
                    <div class="chart-wrapper">
                      <canvas id="social-box-chart-2" height="90"></canvas>
                    </div>
                  </div>
                  <div class="brand-card-body">
                    <div>
                      <div class="text-value">973k</div>
                      <div class="text-uppercase text-muted small">followers</div>
                    </div>
                    <div>
                      <div class="text-value">1.792</div>
                      <div class="text-uppercase text-muted small">tweets</div>
                    </div>
                  </div>
                </div>
              </div>
              <!--/.col-->
              <div class="col-sm-6 col-lg-3">
                <div class="brand-card">
                  <div class="brand-card-header bg-linkedin">
                    <i class="fa fa-linkedin"></i>
                    <div class="chart-wrapper">
                      <canvas id="social-box-chart-3" height="90"></canvas>
                    </div>
                  </div>
                  <div class="brand-card-body">
                    <div>
                      <div class="text-value">500+</div>
                      <div class="text-uppercase text-muted small">contacts</div>
                    </div>
                    <div>
                      <div class="text-value">292</div>
                      <div class="text-uppercase text-muted small">feeds</div>
                    </div>
                  </div>
                </div>
              </div>
              <!--/.col-->
              <div class="col-sm-6 col-lg-3">
                <div class="brand-card">
                  <div class="brand-card-header bg-google-plus">
                    <i class="fa fa-google-plus"></i>
                    <div class="chart-wrapper">
                      <canvas id="social-box-chart-4" height="90"></canvas>
                    </div>
                  </div>
                  <div class="brand-card-body">
                    <div>
                      <div class="text-value">894</div>
                      <div class="text-uppercase text-muted small">followers</div>
                    </div>
                    <div>
                      <div class="text-value">92</div>
                      <div class="text-uppercase text-muted small">circles</div>
                    </div>
                  </div>
                </div>
              </div>
              <!--/.col-->
            </div>
            <!--/.row-->

            <div class="row">
              <div class="col-md-12">
                <div class="card">
                  <div class="card-header">
                    Traffic &amp; Sales
                  </div>
                  <div class="card-body">
                    <div class="row">
                      <div class="col-sm-6">
                        <div class="row">
                          <div class="col-sm-6">
                            <div class="callout callout-info">
                              <small class="text-muted">New Clients</small>
                              <br>
                              <strong class="h4">9,123</strong>
                              <div class="chart-wrapper">
                                <canvas id="sparkline-chart-1" width="100" height="30"></canvas>
                              </div>
                            </div>
                          </div>
                          <!--/.col-->
                          <div class="col-sm-6">
                            <div class="callout callout-danger">
                              <small class="text-muted">Recuring Clients</small>
                              <br>
                              <strong class="h4">22,643</strong>
                              <div class="chart-wrapper">
                                <canvas id="sparkline-chart-2" width="100" height="30"></canvas>
                              </div>
                            </div>
                          </div>
                          <!--/.col-->
                        </div>
                        <!--/.row-->
                        <hr class="mt-0">
                        <div class="progress-group mb-4">
                          <div class="progress-group-prepend">
                            <span class="progress-group-text">
                              Monday
                            </span>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-info" role="progressbar" style="width: 34%" aria-valuenow="34" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-danger" role="progressbar" style="width: 78%" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group mb-4">
                          <div class="progress-group-prepend">
                            <span class="progress-group-text">
                              Tuesday
                            </span>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-info" role="progressbar" style="width: 56%" aria-valuenow="56" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-danger" role="progressbar" style="width: 94%" aria-valuenow="94" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group mb-4">
                          <div class="progress-group-prepend">
                            <span class="progress-group-text">
                              Wednesday
                            </span>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-info" role="progressbar" style="width: 12%" aria-valuenow="12" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-danger" role="progressbar" style="width: 67%" aria-valuenow="67" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group mb-4">
                          <div class="progress-group-prepend">
                            <span class="progress-group-text">
                              Thursday
                            </span>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-info" role="progressbar" style="width: 43%" aria-valuenow="43" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-danger" role="progressbar" style="width: 91%" aria-valuenow="91" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group mb-4">
                          <div class="progress-group-prepend">
                            <span class="progress-group-text">
                              Friday
                            </span>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-info" role="progressbar" style="width: 22%" aria-valuenow="22" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-danger" role="progressbar" style="width: 73%" aria-valuenow="73" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group mb-4">
                          <div class="progress-group-prepend">
                            <span class="progress-group-text">
                              Saturday
                            </span>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-info" role="progressbar" style="width: 53%" aria-valuenow="53" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-danger" role="progressbar" style="width: 82%" aria-valuenow="82" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group mb-4">
                          <div class="progress-group-prepend">
                            <span class="progress-group-text">
                              Sunday
                            </span>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-info" role="progressbar" style="width: 9%" aria-valuenow="9" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-danger" role="progressbar" style="width: 69%" aria-valuenow="69" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!--/.col-->
                      <div class="col-sm-6">
                        <div class="row">
                          <div class="col-sm-6">
                            <div class="callout callout-warning">
                              <small class="text-muted">Pageviews</small>
                              <br>
                              <strong class="h4">78,623</strong>
                              <div class="chart-wrapper">
                                <canvas id="sparkline-chart-3" width="100" height="30"></canvas>
                              </div>
                            </div>
                          </div>
                          <!--/.col-->
                          <div class="col-sm-6">
                            <div class="callout callout-success">
                              <small class="text-muted">Organic</small>
                              <br>
                              <strong class="h4">49,123</strong>
                              <div class="chart-wrapper">
                                <canvas id="sparkline-chart-4" width="100" height="30"></canvas>
                              </div>
                            </div>
                          </div>
                          <!--/.col-->
                        </div>
                        <!--/.row-->
                        <hr class="mt-0">
                        <div class="progress-group">
                          <div class="progress-group-header">
                            <i class="icon-user progress-group-icon"></i>
                            <div>Male</div>
                            <div class="ml-auto font-weight-bold">43%</div>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-warning" role="progressbar" style="width: 43%" aria-valuenow="43" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group mb-5">
                          <div class="progress-group-header">
                            <i class="icon-user-female progress-group-icon"></i>
                            <div>Female</div>
                            <div class="ml-auto font-weight-bold">37%</div>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-warning" role="progressbar" style="width: 43%" aria-valuenow="43" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group">
                          <div class="progress-group-header align-items-end">
                            <i class="icon-globe progress-group-icon"></i>
                            <div>Organic Search</div>
                            <div class="ml-auto font-weight-bold mr-2">191.235</div>
                            <div class="text-muted small">(56%)</div>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-success" role="progressbar" style="width: 56%" aria-valuenow="56" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group">
                          <div class="progress-group-header align-items-end">
                            <i class="icon-social-facebook progress-group-icon"></i>
                            <div>Facebook</div>
                            <div class="ml-auto font-weight-bold mr-2">51.223</div>
                            <div class="text-muted small">(15%)</div>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-success" role="progressbar" style="width: 15%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group">
                          <div class="progress-group-header align-items-end">
                            <i class="icon-social-twitter progress-group-icon"></i>
                            <div>Twitter</div>
                            <div class="ml-auto font-weight-bold mr-2">37.564</div>
                            <div class="text-muted small">(11%)</div>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-success" role="progressbar" style="width: 11%" aria-valuenow="11" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                        <div class="progress-group">
                          <div class="progress-group-header align-items-end">
                            <i class="icon-social-linkedin progress-group-icon"></i>
                            <div>LinkedIn</div>
                            <div class="ml-auto font-weight-bold mr-2">27.319</div>
                            <div class="text-muted small">(8%)</div>
                          </div>
                          <div class="progress-group-bars">
                            <div class="progress progress-xs">
                              <div class="progress-bar bg-success" role="progressbar" style="width: 8%" aria-valuenow="8" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!--/.col-->
                    </div>
                    <!--/.row-->
                    <br/>
                  </div>
                </div>
              </div>
              <!--/.col-->
            </div>
            <!--/.row-->
          </div>

        </div>
      </main>
      <aside class="aside-menu">
        <ul class="nav nav-tabs" role="tablist">
          <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#timeline" role="tab">
              <i class="icon-list"></i>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#messages" role="tab">
              <i class="icon-speech"></i>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#settings" role="tab">
              <i class="icon-settings"></i>
            </a>
          </li>
        </ul>
        <!-- Tab panes-->
        <div class="tab-content">
          <div class="tab-pane active" id="timeline" role="tabpanel">
            <div class="list-group list-group-accent">
              <div class="list-group-item list-group-item-accent-secondary bg-light text-center font-weight-bold text-muted text-uppercase small">Today</div>
              <div class="list-group-item list-group-item-accent-warning list-group-item-divider">
                <div class="avatar float-right">
                  <img class="img-avatar" src="img/avatars/7.jpg" alt="admin@bootstrapmaster.com">
                </div>
                <div>Meeting with
                  <strong>Lucas</strong>
                </div>
                <small class="text-muted mr-3">
                  <i class="icon-calendar"></i>  1 - 3pm</small>
                <small class="text-muted">
                  <i class="icon-location-pin"></i>  Palo Alto, CA</small>
              </div>
              <div class="list-group-item list-group-item-accent-info">
                <div class="avatar float-right">
                  <img class="img-avatar" src="img/avatars/4.jpg" alt="admin@bootstrapmaster.com">
                </div>
                <div>Skype with
                  <strong>Megan</strong>
                </div>
                <small class="text-muted mr-3">
                  <i class="icon-calendar"></i>  4 - 5pm</small>
                <small class="text-muted">
                  <i class="icon-social-skype"></i>  On-line</small>
              </div>
              <div class="list-group-item list-group-item-accent-secondary bg-light text-center font-weight-bold text-muted text-uppercase small">Tomorrow</div>
              <div class="list-group-item list-group-item-accent-danger list-group-item-divider">
                <div>New UI Project -
                  <strong>deadline</strong>
                </div>
                <small class="text-muted mr-3">
                  <i class="icon-calendar"></i>  10 - 11pm</small>
                <small class="text-muted">
                  <i class="icon-home"></i>  creativeLabs HQ</small>
                <div class="avatars-stack mt-2">
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/2.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/3.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/4.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/5.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/6.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                </div>
              </div>
              <div class="list-group-item list-group-item-accent-success list-group-item-divider">
                <div>
                  <strong>#10 Startups.Garden</strong> Meetup</div>
                <small class="text-muted mr-3">
                  <i class="icon-calendar"></i>  1 - 3pm</small>
                <small class="text-muted">
                  <i class="icon-location-pin"></i>  Palo Alto, CA</small>
              </div>
              <div class="list-group-item list-group-item-accent-primary list-group-item-divider">
                <div>
                  <strong>Team meeting</strong>
                </div>
                <small class="text-muted mr-3">
                  <i class="icon-calendar"></i>  4 - 6pm</small>
                <small class="text-muted">
                  <i class="icon-home"></i>  creativeLabs HQ</small>
                <div class="avatars-stack mt-2">
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/2.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/3.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/4.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/5.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/6.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/7.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                  <div class="avatar avatar-xs">
                    <img class="img-avatar" src="img/avatars/8.jpg" alt="admin@bootstrapmaster.com">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="tab-pane p-3" id="messages" role="tabpanel">
            <div class="message">
              <div class="py-3 pb-5 mr-3 float-left">
                <div class="avatar">
                  <img class="img-avatar" src="img/avatars/7.jpg" alt="admin@bootstrapmaster.com">
                  <span class="avatar-status badge-success"></span>
                </div>
              </div>
              <div>
                <small class="text-muted">Lukasz Holeczek</small>
                <small class="text-muted float-right mt-1">1:52 PM</small>
              </div>
              <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
              <small class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</small>
            </div>
            <hr>
            <div class="message">
              <div class="py-3 pb-5 mr-3 float-left">
                <div class="avatar">
                  <img class="img-avatar" src="img/avatars/7.jpg" alt="admin@bootstrapmaster.com">
                  <span class="avatar-status badge-success"></span>
                </div>
              </div>
              <div>
                <small class="text-muted">Lukasz Holeczek</small>
                <small class="text-muted float-right mt-1">1:52 PM</small>
              </div>
              <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
              <small class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</small>
            </div>
            <hr>
            <div class="message">
              <div class="py-3 pb-5 mr-3 float-left">
                <div class="avatar">
                  <img class="img-avatar" src="img/avatars/7.jpg" alt="admin@bootstrapmaster.com">
                  <span class="avatar-status badge-success"></span>
                </div>
              </div>
              <div>
                <small class="text-muted">Lukasz Holeczek</small>
                <small class="text-muted float-right mt-1">1:52 PM</small>
              </div>
              <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
              <small class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</small>
            </div>
            <hr>
            <div class="message">
              <div class="py-3 pb-5 mr-3 float-left">
                <div class="avatar">
                  <img class="img-avatar" src="img/avatars/7.jpg" alt="admin@bootstrapmaster.com">
                  <span class="avatar-status badge-success"></span>
                </div>
              </div>
              <div>
                <small class="text-muted">Lukasz Holeczek</small>
                <small class="text-muted float-right mt-1">1:52 PM</small>
              </div>
              <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
              <small class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</small>
            </div>
            <hr>
            <div class="message">
              <div class="py-3 pb-5 mr-3 float-left">
                <div class="avatar">
                  <img class="img-avatar" src="img/avatars/7.jpg" alt="admin@bootstrapmaster.com">
                  <span class="avatar-status badge-success"></span>
                </div>
              </div>
              <div>
                <small class="text-muted">Lukasz Holeczek</small>
                <small class="text-muted float-right mt-1">1:52 PM</small>
              </div>
              <div class="text-truncate font-weight-bold">Lorem ipsum dolor sit amet</div>
              <small class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt...</small>
            </div>
          </div>
          <div class="tab-pane p-3" id="settings" role="tabpanel">
            <h6>Settings</h6>
            <div class="aside-options">
              <div class="clearfix mt-4">
                <small>
                  <b>Option 1</b>
                </small>
                <label class="switch switch-label switch-pill switch-success switch-sm float-right">
                  <input class="switch-input" type="checkbox" checked="">
                  <span class="switch-slider" data-checked="On" data-unchecked="Off"></span>
                </label>
              </div>
              <div>
                <small class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</small>
              </div>
            </div>
            <div class="aside-options">
              <div class="clearfix mt-3">
                <small>
                  <b>Option 2</b>
                </small>
                <label class="switch switch-label switch-pill switch-success switch-sm float-right">
                  <input class="switch-input" type="checkbox">
                  <span class="switch-slider" data-checked="On" data-unchecked="Off"></span>
                </label>
              </div>
              <div>
                <small class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</small>
              </div>
            </div>
            <div class="aside-options">
              <div class="clearfix mt-3">
                <small>
                  <b>Option 3</b>
                </small>
                <label class="switch switch-label switch-pill switch-success switch-sm float-right">
                  <input class="switch-input" type="checkbox">
                  <span class="switch-slider" data-checked="On" data-unchecked="Off"></span>
                </label>
              </div>
            </div>
            <div class="aside-options">
              <div class="clearfix mt-3">
                <small>
                  <b>Option 4</b>
                </small>
                <label class="switch switch-label switch-pill switch-success switch-sm float-right">
                  <input class="switch-input" type="checkbox" checked="">
                  <span class="switch-slider" data-checked="On" data-unchecked="Off"></span>
                </label>
              </div>
            </div>
            <hr>
            <h6>System Utilization</h6>
            <div class="text-uppercase mb-1 mt-4">
              <small>
                <b>CPU Usage</b>
              </small>
            </div>
            <div class="progress progress-xs">
              <div class="progress-bar bg-info" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <small class="text-muted">348 Processes. 1/4 Cores.</small>
            <div class="text-uppercase mb-1 mt-2">
              <small>
                <b>Memory Usage</b>
              </small>
            </div>
            <div class="progress progress-xs">
              <div class="progress-bar bg-warning" role="progressbar" style="width: 70%" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <small class="text-muted">11444GB/16384MB</small>
            <div class="text-uppercase mb-1 mt-2">
              <small>
                <b>SSD 1 Usage</b>
              </small>
            </div>
            <div class="progress progress-xs">
              <div class="progress-bar bg-danger" role="progressbar" style="width: 95%" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <small class="text-muted">243GB/256GB</small>
            <div class="text-uppercase mb-1 mt-2">
              <small>
                <b>SSD 2 Usage</b>
              </small>
            </div>
            <div class="progress progress-xs">
              <div class="progress-bar bg-success" role="progressbar" style="width: 10%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <small class="text-muted">25GB/256GB</small>
          </div>
        </div>
      </aside>
    </div>
    <footer class="app-footer">
      <div>
        <a href="https://coreui.io">CoreUI</a>
        <span>&copy; 2018 creativeLabs.</span>
      </div>
      <div class="ml-auto">
        <span>Powered by</span>
        <a href="https://coreui.io">CoreUI</a>
      </div>
    </footer>
    <!-- Bootstrap and necessary plugins-->
    <script src="vendors/js/jquery.min.js"></script>
    <script src="vendors/js/popper.min.js"></script>
    <script src="vendors/js/bootstrap.min.js"></script>
    <script src="vendors/js/pace.min.js"></script>
    <script src="vendors/js/perfect-scrollbar.min.js"></script>
    <script src="vendors/js/coreui.min.js"></script>
    <!-- Plugins and scripts required by this view-->
    <script src="vendors/js/Chart.min.js"></script>
    <script src="vendors/js/custom-tooltips.min.js"></script>
    <script src="js/main.js"></script>
	<script>
		$(document).ready(function() {
			$("#dashboard a.nav-link").addClass("active");
		});
	</script>
</body>
</html>