package fr.uge.lootin.back.ws;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
	Set<WebSocketSession> set = new HashSet<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		set.add(session);
		String payload = message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);
		if (jsonObject.getBoolean("connexion")){
			return ;
		}
		for (WebSocketSession f : set) {
			f.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
		}
		System.out.println(set);
	}
}