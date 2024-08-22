package com.hotel.bean;

public class Reservation {
	    String check_in_date;
	    String check_out_date;
	    String room_Preference;
	    String name;
	    String contact;
	    
	  	    
		public Reservation(String check_in_date, String check_out_date, String room_Preference, String name,
				String contact) {
			super();
			this.check_in_date = check_in_date;
			this.check_out_date = check_out_date;
			this.room_Preference = room_Preference;
			this.name = name;
			this.contact = contact;
		}
		
		public Reservation() {
			// TODO Auto-generated constructor stub
		}
		public String getCheck_in_date() {
			return check_in_date;
		}
		public void setCheck_in_date( String check_in_date) {
			this.check_in_date = check_in_date;
		}
		public String getCheck_out_date() {
			return check_out_date;
		}
		public void setCheck_out_date(String check_out_date) {
			this.check_out_date = check_out_date;
		}
		public String getPreferences() {
			return room_Preference;
		}
		public void setPreferences(String room_Preference) {
			this.room_Preference = room_Preference;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
	    
	    
	    

}
