<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="./resource/script/highcharts.js"></script>
<script src="./resource/script/sample/series-label.js"></script>
<script src="./resource/script/sample/exporting.js"></script>
<script src="./resource/script/sample/export-data.js"></script>
   <script src="./resource/script/afes/main.js"></script>
   
<link rel="stylesheet" type="text/css" href="./resource/style/datatables.min.css">
  <link rel="stylesheet" type="text/css" href="./resource/style/main.css">
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
      <img src="https://placehold.it/1200x400?text=Another Image Maybe" alt="Image">
        <div class="carousel-caption">
          <div id="orderChart"></div>
        </div>      
      </div>

      <div class="item">
        <img src="https://placehold.it/1200x400?text=Another Image Maybe" alt="Image">
        <div class="carousel-caption">
          <table id="example" class="display" style="width:100%">
     <thead>
         <tr>
             <th>Name</th>
             <th>Position</th>
             <th>Office</th>
             <th>Age</th>
             <th>Start date</th>
             <th>Salary</th>
         </tr>
     </thead>
     <tbody>
         <tr>
             <td>Tiger Nixon</td>
             <td>System Architect</td>
             <td>Edinburgh</td>
             <td>61</td>
             <td>2011/04/25</td>
             <td>$320,800</td>
         </tr>
         <tr>
             <td>Garrett Winters</td>
             <td>Accountant</td>
             <td>Tokyo</td>
             <td>63</td>
             <td>2011/07/25</td>
             <td>$170,750</td>
         </tr>
         <tr>
             <td>Ashton Cox</td>
             <td>Junior Technical Author</td>
             <td>San Francisco</td>
             <td>66</td>
             <td>2009/01/12</td>
             <td>$86,000</td>
         </tr>
         <tr>
             <td>Cedric Kelly</td>
             <td>Senior Javascript Developer</td>
             <td>Edinburgh</td>
             <td>22</td>
             <td>2012/03/29</td>
             <td>$433,060</td>
         </tr>
         <tr>
             <td>Airi Satou</td>
             <td>Accountant</td>
             <td>Tokyo</td>
             <td>33</td>
             <td>2008/11/28</td>
             <td>$162,700</td>
         </tr>
         <tr>
             <td>Brielle Williamson</td>
             <td>Integration Specialist</td>
             <td>New York</td>
             <td>61</td>
             <td>2012/12/02</td>
             <td>$372,000</td>
         </tr>
         <tr>
             <td>Herrod Chandler</td>
             <td>Sales Assistant</td>
             <td>San Francisco</td>
             <td>59</td>
             <td>2012/08/06</td>
             <td>$137,500</td>
         </tr>
         <tr>
             <td>Rhona Davidson</td>
             <td>Integration Specialist</td>
             <td>Tokyo</td>
             <td>55</td>
             <td>2010/10/14</td>
             <td>$327,900</td>
         </tr>
         <tr>
             <td>Colleen Hurst</td>
             <td>Javascript Developer</td>
             <td>San Francisco</td>
             <td>39</td>
             <td>2009/09/15</td>
             <td>$205,500</td>
         </tr>
         <tr>
             <td>Sonya Frost</td>
             <td>Software Engineer</td>
             <td>Edinburgh</td>
             <td>23</td>
             <td>2008/12/13</td>
             <td>$103,600</td>
         </tr>
         <tr>
             <td>Jena Gaines</td>
             <td>Office Manager</td>
             <td>London</td>
             <td>30</td>
             <td>2008/12/19</td>
             <td>$90,560</td>
         </tr>
         <tr>
             <td>Quinn Flynn</td>
             <td>Support Lead</td>
             <td>Edinburgh</td>
             <td>22</td>
             <td>2013/03/03</td>
             <td>$342,000</td>
         </tr>
         <tr>
             <td>Charde Marshall</td>
             <td>Regional Director</td>
             <td>San Francisco</td>
             <td>36</td>
             <td>2008/10/16</td>
             <td>$470,600</td>
         </tr>
         <tr>
             <td>Haley Kennedy</td>
             <td>Senior Marketing Designer</td>
             <td>London</td>
             <td>43</td>
             <td>2012/12/18</td>
             <td>$313,500</td>
         </tr>
     </tbody>
     <tfoot>
         <tr>
             <th>Name</th>
             <th>Position</th>
             <th>Office</th>
             <th>Age</th>
             <th>Start date</th>
             <th>Salary</th>
         </tr>
     </tfoot>
 </table>
        </div>      
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
  
<div class="container text-center">    
  <div class="row">
    <div class="col-sm-6">
      <img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image">
      <p>Order List</p>
    </div>
    </div>
  </div>
</div><br>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>

</body>
</html>
