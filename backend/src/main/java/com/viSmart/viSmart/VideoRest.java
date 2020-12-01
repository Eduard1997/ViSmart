package com.viSmart.viSmart;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class VideoRest{

	private String meetingId;
	private String URI;

	private ArrayList<Video> Videos = new ArrayList<>();

	private ViCoordinator coordinator;
	private VideoStream videoStream;

	public VideoRest( ViCoordinator coord){
		Random random = new Random();
		coordinator = coord;
		meetingId = UUID.randomUUID().toString();
		URI = "";
	}

	public VideoStream getState() {
		return videoStream;
	}

	public void setSettings(User user,Settings settings)
	{
		if (user.equals(coordinator)){
			videoStream = new VideoStream(settings);
			notifyAllObservers();
		}
	}

	public void attach(User user){
		Video observer = new Video(user);
		Videos.add(observer);
	}

	public void notifyAllObservers(){
		for (Video observer : Videos) {
			observer.update();
		}
	}

	public boolean checkCredits(String url_link, String id){
		return (URI.equals(url_link) && meetingId.equals(id));
	}
}