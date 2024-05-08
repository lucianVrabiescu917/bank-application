package com.example.bankapplication.services;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.enums.RolesEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionWebSocketHandler extends TextWebSocketHandler {

    protected static final Logger log = LoggerFactory.getLogger(BaseService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, List<WebSocketSession>> sessionsMap = new HashMap<>();

    private final List<String> ROLES = List.of("IMPORTER", "EXPORTER", "BANK");

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String role = extractRoleFromSession(session);
        if (role != null || !ROLES.contains(role)) {
            List<WebSocketSession> sessions = sessionsMap.get(role);
            if (sessions == null) {
                sessions = new CopyOnWriteArrayList<>();
            }
            sessions.add(session);
            sessionsMap.put(role, sessions);
        } else {
            session.close();
        }
    }

    private String extractRoleFromSession(WebSocketSession session) {
        String role = null;
        URI uri = session.getUri();
        if (uri != null && uri.getQuery() != null) {
            String queryString = uri.getQuery();
            String[] queryParams = queryString.split("&");
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2 && keyValue[0].equals("role")) {
                    role = keyValue[1];
                }
            }
        }
        return role;
    }

    public void sendTransaction(TransactionDto transactionDto, RolesEnum role) {
        List<WebSocketSession> sessions = sessionsMap.get(role.toString());
        if (sessions != null) {
            sessions.forEach(session -> {
                if (session.isOpen()) {
                    TextMessage message;
                    try {
                        message = new TextMessage(objectMapper.writeValueAsString(transactionDto));
                        session.sendMessage(message);
                    } catch (IOException e) {
                        log.error("Websocket sendingTransaction failed for session {}", session.getId());
                    }
                }
            });
        }
    }


}
