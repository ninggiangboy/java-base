package dev.ngb.issues_logging_app.infrastructure.cache;

public class CacheConstant {
    public static final String TAGS_CACHE_NAME = "tags";
    public static final String ALL_TAGS_KEY_NAME = "all_tags";

    public static final String CATEGORIES_CACHE_NAME = "categories";
    public static final String ALL_CATEGORIES_KEY_NAME = "all_categories";

    public static final String PROJECTS_CACHE_NAME = "projects";
    public static final String USER_PROJECTS_KEY_PATTERN = "user_projects_%s";

    public static final String USERS_CACHE_NAME = "users";
    public static final String ALL_MENTION_USERS_KEY_NAME = "all_users";
    public static final String MENTION_USERS_PROJECT_KEY_NAME = "mention_users_project_%s_%s";

    public static final String REFRESH_TOKEN_CACHE_NAME = "refresh_tokens";
    public static final String VERIFICATION_TOKEN_CACHE_NAME = "verification_tokens";
    public static final String REFRESH_TOKEN_KEY_PATTERN = "refresh_%s";
    public static final String VERIFICATION_TOKEN_KEY_PATTERN = "verification_%s";
}
