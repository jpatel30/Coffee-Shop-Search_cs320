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
      html, body, #map-canvas {
        height: 500px;
        width: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
</head>
 
 
  <body>
    <div id="map-canvas"></div>
	 	<script type="text/javascript">
	 	<c:choose>
	 	<c:when test="${coffeeBean == null}">
	 	
		
		
		var latitude = new Array();
	    var longitude = new Array();
	    <c:forEach items="${CoffeeList}" var="item" varStatus="count">
	    	latitude["${count.index}"] = "${item.latitude}";
	    	longitude["${count.index}"] = "${item.longitude}";
	    </c:forEach>
	       
		var map;
	
		function initialize() {
		  var mapOptions = {
		    zoom: 13
		  };
		  map = new google.maps.Map(document.getElementById('map-canvas'),
		      mapOptions);
	
		  // Try HTML5 geolocation
		  if(navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(function(position) {
		      var pos = new google.maps.LatLng(position.coords.latitude,
		                                       position.coords.longitude);
		    
		      var marker = new google.maps.Marker({
		    	    position: pos,
		    	    animation:google.maps.Animation.BOUNCE,
		    	    map: map
		    	  });
		      
		     for (var i = 0; i < latitude.length; i++) {  
		          marker = new google.maps.Marker({
		            position: new google.maps.LatLng(latitude[i], longitude[i]),
		            map: map
		          });
		      }    
		      
	
		      map.setCenter(pos);
		    }, function() {
		      handleNoGeolocation(true);
		    });
		  } else {
		    // Browser doesn't support Geolocation
		    handleNoGeolocation(false);
		  }
		}
	
		function handleNoGeolocation(errorFlag) {
		  if (errorFlag) {
		    var content = 'Error: The Geolocation service failed.';
		  } else {
		    var content = 'Error: Your browser doesn\'t support geolocation.';
		  }
	
		  var options = {
		    map: map,
		    position: new google.maps.LatLng(60, 105),
		    content: content
		  };
	
		  var infowindow = new google.maps.InfoWindow(options);
		  map.setCenter(options.position);
		}
	
		google.maps.event.addDomListener(window, 'load', initialize);
		</c:when>
		<c:otherwise>
		
		var latitude = "${coffeeBean.latitude}";
	    var longitude ="${coffeeBean.longitude}";
	    
		var map;
		
		function initialize() {
		  var mapOptions = {
		    zoom: 13
		  };
		  map = new google.maps.Map(document.getElementById('map-canvas'),
		      mapOptions);
	
		  // Try HTML5 geolocation
		  if(navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(function(position) {
		      var pos = new google.maps.LatLng(position.coords.latitude,
		                                       position.coords.longitude);
		    
		      var marker = new google.maps.Marker({
		    	  	position: new google.maps.LatLng(latitude, longitude),
		    	    animation:google.maps.Animation.BOUNCE,
		    	    map: map
		    	  });
		      
		       
		      map.setCenter(pos);
		    }, function() {
		      handleNoGeolocation(true);
		    });
		  } else {
		    // Browser doesn't support Geolocation
		    handleNoGeolocation(false);
		  }
		}
	
		function handleNoGeolocation(errorFlag) {
		  if (errorFlag) {
		    var content = 'Error: The Geolocation service failed.';
		  } else {
		    var content = 'Error: Your browser doesn\'t support geolocation.';
		  }
	
		  var options = {
		    map: map,
		    position: new google.maps.LatLng(60, 105),
		    content: content
		  };
	
		  var infowindow = new google.maps.InfoWindow(options);
		  map.setCenter(options.position);
		}
	
		google.maps.event.addDomListener(window, 'load', initialize);
	
		</c:otherwise>
	 	</c:choose>
				
    </script>
 
 
  </body>
</html>