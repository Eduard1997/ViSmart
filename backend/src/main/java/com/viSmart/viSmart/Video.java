package com.viSmart.viSmart;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Objects;

public class Video {

	private int volume;
	private int image;
	private Session session;

    public Account account;

    public Video(Account acc, Session ses){
        account = acc;
        session = ses;
    }

    public void update(String data) {
        try {
            session.getBasicRemote().sendText(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account acc = (Account) o;
        return Objects.equals(account, acc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}