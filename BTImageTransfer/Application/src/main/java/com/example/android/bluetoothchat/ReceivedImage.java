package com.example.android.bluetoothchat;


public class ReceivedImage {
	private long id;
	private String imageSize;
	private String Uri;
	private String lastUpdatedTime;


	public long getId() {
	return id;
	}

	public void setId(long id) {
	this.id = id;
	}

	public String getImageSize() {
	return imageSize;
	}

	public void setImageSize(String imageSize) {
	this.imageSize = imageSize;
	}

	public String getUri() {
		return Uri;
	}

	public void setUri(String Uri) {
		this.Uri = Uri;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
	return Uri;
	}
}