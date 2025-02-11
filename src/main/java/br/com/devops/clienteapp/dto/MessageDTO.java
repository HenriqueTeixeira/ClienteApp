package br.com.devops.clienteapp.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String systemId;
    private String title;
    private String body;

    public MessageDTO() {}

    public MessageDTO(String systemId, String title, String body) {
        this.systemId = systemId;
        this.title = title;
        this.body = body;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}