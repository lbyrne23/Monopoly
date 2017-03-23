package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//

import java.util.ArrayList;

public class PropertyList extends ArrayList<Property>{
	public PropertyList(){
		add(new Property("GO"));
		add(new Property("Old Kent Road","Kent", -1, 60, SITE_RENTS[0], 0, 50, 50));
		add(new Property("Community Chest"));
		add(new Property("Whitechapel Rd","Whitechapel", -1, 60, SITE_RENTS[1], 0, 50, 50));
		add(new Property("Income Tax", SITE_RENTS[2]));
		add(new Property("King's Cross Station", "Kings", -1, 200, SITE_RENTS[3], 8, 25, -1));
		add(new Property("The Angel Islington", "Angel",-1, 100, SITE_RENTS[4], 1, 50, 50));
		add(new Property("Chance"));
		add(new Property("Euston Rd", "Euston", -1, 100, SITE_RENTS[5], 1, 50, 50));
		add(new Property("Pentonville Rd", "Pentonville", -1, 120, SITE_RENTS[6], 1, 50, 50));
		add(new Property("Jail"));
		add(new Property("Pall Mall", "Mall", -1, 140, SITE_RENTS[7], 2, 60, 100));
		add(new Property("Electric Company", "Electric", -1, 150, SITE_RENTS[8], 9, 50, -1)); 
		add(new Property("Whitehall", "Whitehall", -1, 140, SITE_RENTS[9], 2, 70, 100));
		add(new Property("Northumberland Ave", "Northumberland", -1, 160, SITE_RENTS[10], 2, 70, 100));
		add(new Property("Marylebone Station", "Marylebone",-1, 200, SITE_RENTS[11], 8, 100, -1));
		add(new Property("Bow St","Bow", -1, 180, SITE_RENTS[12], 3, 80, 100));
		add(new Property("Community Chest"));
		add(new Property("Marlborough St","Marlborough", -1, 180, SITE_RENTS[13], 3, 90, 100));
		add(new Property("Vine St","Vine", -1, 200, SITE_RENTS[14], 3, 90, 100));
		add(new Property("Free Parking"));
		add(new Property("Strand","Strand", -1, 220, SITE_RENTS[15], 4, 100, 150));
		add(new Property("Chance"));
		add(new Property("Fleet St","Fleet", -1, 220, SITE_RENTS[16], 4, 110, 150));
		add(new Property("Trafalgar Sq","Trafalgar", -1, 240, SITE_RENTS[17], 4, 110, 150));
		add(new Property("Fenchurch St Station","Fenchurch", -1, 200, SITE_RENTS[18], 8, 200, -1));
		add(new Property("Leicester Sq","Leicester", -1, 260, SITE_RENTS[19], 5, 120, 150));
		add(new Property("Coventry St","Coventry", -1, 260, SITE_RENTS[20], 5, 150, 150));
		add(new Property("Waterworks","Waterworks",-1, 150, SITE_RENTS[21], 9, 200, -1));
		add(new Property("Piccadilly","Piccadilly", -1, 280, SITE_RENTS[22], 5, 150, 150));
		add(new Property("Go To Jail"));
		add(new Property("Regent St","Regent", -1, 300, SITE_RENTS[23], 6, 150, 150));
		add(new Property("Oxford St","Oxford", -1, 300, SITE_RENTS[24], 6, 200, 200));
		add(new Property("Community Chest"));
		add(new Property("Bond St","Bond", -1, 320, SITE_RENTS[25], 6, 200, 200));
		add(new Property("Liverpool St Station","Liverpool", -1, 200, SITE_RENTS[26], 8, 200, 200));
		add(new Property("Chance"));
		add(new Property("Park Lane","Park", -1, 350, SITE_RENTS[27], 7, 175, 200));
		add(new Property("Super Tax", SITE_RENTS[28]));
		add(new Property("Mayfair","Mayfair", -1, 400, SITE_RENTS[29], 7, 200, 200));
	}
	
	private static final int[][] SITE_RENTS = {
			{2,10,30,90,160,250},
			{4,20,60,180,320,450},
			
			{200},					//IncomeTax
			{25,50,100,200,200,200}, //Station

			{6,30,90,270,400,550},
			{6,30,90,270,400,550},
			{8,40,100,300,450,600},

			{10,50,150,450,625,750},
			{4,10,0,0,0,0},				 //Utility
			{10,50,150,450,625,750},
			{12,60,180,500,700,900},
			{25,50,100,200,200,200},	 //Station
			
			{14,70,200,550,750,950},
			{14,70,200,550,750,950},
			{16,80,220,600,800,1000},

			{18,90,250,700,875,1050},
			{18,90,250,700,875,1050},
			{20,100,300,750,925,1100},
			{25,50,100,200,200,200}, 	// Station
			
			{22,110,330,800,975,1150}
			,{22,110,330,800,975,1150},
			{4,10,0,0,0,0}, 			//Utility
			{22,120,360,850,1025,1200},

			{26,130,390,900,1100,1275},
			{26,130,390,900,1100,1275},
			{28,150,450,1000,1200,1400},
			{25,50,100,200,200,200}, 	// Station
			
			{25,50,100,200,200,200},
			{100},						//Super Tax
			{35,175,500,1100,1300,1500}
			};
	
			
}