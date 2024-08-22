package com.hotel.bean;

import java.sql.Date;

public class Room {
 String roomID;
 Date check_in_date1;
 Date check_out_date2;
 String preference;
 
 
public Room(String roomID, Date check_in_date1, Date check_out_date2, String preference) {
	super();
	this.roomID = roomID;
	this.check_in_date1 = check_in_date1;
	this.check_out_date2 = check_out_date2;
	this.preference = preference;
}
public String getRoomID() {
	return roomID;
}
public void setRoomID(String roomID) {
	this.roomID = roomID;
}
public Date getCheck_in_date1() {
	return check_in_date1;
}
public void setCheck_in_date1(Date check_in_date1) {
	this.check_in_date1 = check_in_date1;
}
public Date getCheck_out_date2() {
	return check_out_date2;
}
public void setCheck_out_date2(Date check_out_date2) {
	this.check_out_date2 = check_out_date2;
}
public String getPreference() {
	return preference;
}
public void setPreference(String preference) {
	this.preference = preference;
}
 
 
 
}
