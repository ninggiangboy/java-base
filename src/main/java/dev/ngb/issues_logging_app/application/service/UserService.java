package dev.ngb.issues_logging_app.application.service;

import dev.ngb.issues_logging_app.application.dto.user.UserMentionResponse;

import java.util.List;

public interface UserService {
    List<UserMentionResponse> getMentionUser(Integer projectId);
}
