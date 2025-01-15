-- Insert users
INSERT INTO users (username, full_name, email, hash_password, is_admin, is_blocked)
VALUES ('john_doe', 'John Doe', 'khanhhd@rabiloo.com', '$2a$12$BwOmmx4WNmqrMON83lke6.zjSpkavrOSYdrjie8gxCmkyWdGCsV.W',
        TRUE, FALSE),
       ('jane_smith', 'Jane Smith', 'jane.smith@rabiloo.com',
        '$2a$12$BwOmmx4WNmqrMON83lke6.zjSpkavrOSYdrjie8gxCmkyWdGCsV.W', FALSE, FALSE),
       ('alice_brown', 'Alice Brown', 'alice.brown@rabiloo.com',
        '$2a$12$BwOmmx4WNmqrMON83lke6.zjSpkavrOSYdrjie8gxCmkyWdGCsV.W', FALSE, TRUE);

-- Insert projects
INSERT INTO projects (project_name, description)
VALUES ('Project Alpha', 'A description for Project Alpha'),
       ('Project Beta', 'A description for Project Beta');

-- Insert categories
INSERT INTO categories (name)
VALUES ('Bug'),
       ('Feature'),
       ('Task');

-- Insert issues
INSERT INTO issues (project_id, category_id, status, review_assignee_id, reporter_id, title, description)
VALUES (1, 1, 'Open', (SELECT user_id FROM users WHERE username = 'jane_smith'),
        (SELECT user_id FROM users WHERE username = 'john_doe'), 'Fix login bug',
        'There is an issue with the login functionality.'),
       (1, 2, 'In Progress', (SELECT user_id FROM users WHERE username = 'john_doe'),
        (SELECT user_id FROM users WHERE username = 'jane_smith'), 'Add search feature',
        'Implement a search bar on the homepage.'),
       (2, 3, 'Closed', NULL, (SELECT user_id FROM users WHERE username = 'alice_brown'), 'Update documentation',
        'Revise the user manual for the new release.');

-- Insert tags
INSERT INTO tags (tag_name)
VALUES ('Urgent'),
       ('Backend'),
       ('Frontend');

-- Insert issue_tags
INSERT INTO issue_tags (issue_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (2, 3);
-- Assign users to projects
INSERT INTO project_users (project_id, user_id)
VALUES (1, (SELECT user_id FROM users WHERE username = 'john_doe')),
       (1, (SELECT user_id FROM users WHERE username = 'jane_smith')),
       (2, (SELECT user_id FROM users WHERE username = 'alice_brown'));
