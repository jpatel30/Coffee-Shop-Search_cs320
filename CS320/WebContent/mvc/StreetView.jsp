<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>
    <title>CS3 Coffee Shop Locator</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
       html, body, #pano {
        height: 500px;
        width: 500px;
        margin: 0px;
        padding: 0px
      }
    </style>
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
</head>
 
 
  <body>
      <div id="pano"></div>
	 	<script type="text/javascript">
	
		var latitude = "${coffeeBean.latitude}";
	    var longitude ="${coffeeBean.longitude}";
	    


	    function initialize() {
	      var fenway = new google.maps.LatLng(latitude,longitude);
	      var mapOptions = {
	        center: fenway,
	        zoom: 14
	      };
	      var map = new google.maps.Map(
	          document.getElementById('map-canvas'), mapOptions);
	      var panoramaOptions = {
	        position: fenway,
	        pov: {
	          heading: 34,
	          pitch: 10
	        }
	      };
	      var panorama = new  google.maps.StreetViewPanorama(document.getElementById('pano'),panoramaOptions);
	      map.setStreetView(panorama);
	    }

	    google.maps.event.addDomListener(window, 'load', initialize);

	    </script>
 
 
  </body>
</html>