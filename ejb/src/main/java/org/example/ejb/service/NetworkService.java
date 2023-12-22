package org.example.ejb.service;

import javax.ws.rs.client.WebTarget;

public interface NetworkService {
    WebTarget getTarget();
}
