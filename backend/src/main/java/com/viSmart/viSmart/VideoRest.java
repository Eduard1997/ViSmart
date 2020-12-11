package com.viSmart.viSmart;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class VideoRest{

	private String meetingId;
	private String URI;

	private ArrayList<Video> Videos = new ArrayList<>();

	private User coordinator;
	private String data;
	private VideoStream videoStream;

	public VideoRest( User coord){
		Random random = new Random();
		coordinator = coord;
		meetingId = UUID.randomUUID().toString();
		URI = "";
	}

	public VideoStream getState() {
		return videoStream;
	}

	public void setSettings(User user,Settings settings,String data)
	{
		if (user.equals(coordinator)){
			videoStream = new VideoStream(settings);
			notifyAllObservers(user,data);
		}
	}

	public boolean isViCoordinator(User user) {
		return (coordinator.equals(user));
	}

	public void attach(User user, Session session){
		Video observer = new Video(user,session);
		Videos.add(observer);
	}

	public void notifyAllObservers(User user,String data){
		for (Video observer : Videos)
		if (!user.equals(observer.account)){
			observer.update(data);
		}
	}

	public void removeVideo(User user)
	{
		if (!user.equals(coordinator)) {
			Videos.remove(user);
		}
	}

	public boolean checkCredits(String url_link, String id){
		return (URI.equals(url_link) && meetingId.equals(id));
	}
}