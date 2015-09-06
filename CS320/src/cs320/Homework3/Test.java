package cs320.Homework3;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String latitude = "34.0664951";
		String longitude = "-118.1666151";
		
		String[] lat = {"34.066958908","34.06251526"};
		String[] lon = {"-118.1665413","-118.3453674"};

		StringBuilder imageUrl = new StringBuilder();
		imageUrl.append("http://maps.googleapis.com/maps/api/staticmap?center=");
		imageUrl.append(latitude+","+longitude);
		imageUrl.append("&zoom=15&size=2500x2500&markers=color:red%7Clabel:U%7C");
		//for(int i=0;i<lat.length;i++){
		imageUrl.append(latitude+","+longitude);	
		imageUrl.append("&markers=color:blue%7Clabel:S%7C");
		imageUrl.append(lat[0]+","+lon[0]);
		//}	
		System.out.println(imageUrl);
	}

}
