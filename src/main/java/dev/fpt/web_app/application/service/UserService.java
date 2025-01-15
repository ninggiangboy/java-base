package dev.fpt.web_app.application.service;

import dev.ngb.web_app.application.dto.user.UserMentionResponse;

import java.util.List;

public interface UserService {
    List<UserMentionResponse> getMentionUser(Integer projectId);
}
