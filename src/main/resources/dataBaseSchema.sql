CREATE DATABASE gamifiedHabitTracker;

-- 1. app_users
CREATE TABLE app_users (
                           app_user_id SERIAL PRIMARY KEY,
                           username VARCHAR(100) NOT NULL UNIQUE,
                           email VARCHAR(150) NOT NULL UNIQUE,
                           password TEXT NOT NULL,
                           level INT DEFAULT 1,
                           xp INT DEFAULT 0,
                           profile_image TEXT,
                           is_verified BOOLEAN DEFAULT FALSE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. achievements

CREATE TABLE achievements (
                              achievement_id SERIAL PRIMARY KEY,
                              title VARCHAR(150) NOT NULL,
                              description TEXT,
                              badge TEXT,
                              xp_required INT NOT NULL
);

-- 3. app_user_achievements

CREATE TABLE app_user_achievements (
                                       app_user_achievement_id SERIAL PRIMARY KEY,
                                       app_user_id INT NOT NULL,
                                       achievement_id INT NOT NULL,

                                       CONSTRAINT fk_user
                                           FOREIGN KEY (app_user_id)
                                               REFERENCES app_users(app_user_id)
                                               ON DELETE CASCADE,

                                       CONSTRAINT fk_achievement
                                           FOREIGN KEY (achievement_id)
                                               REFERENCES achievements(achievement_id)
                                               ON DELETE CASCADE,

                                       CONSTRAINT unique_user_achievement UNIQUE (app_user_id, achievement_id)
);


-- 4. habits

CREATE TABLE habits (
                        habit_id SERIAL PRIMARY KEY,
                        title VARCHAR(150) NOT NULL,
                        description TEXT,
                        frequency VARCHAR(50),
                        is_active BOOLEAN DEFAULT TRUE,
                        app_user_id INT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                        CONSTRAINT fk_habit_user
                            FOREIGN KEY (app_user_id)
                                REFERENCES app_users(app_user_id)
                                ON DELETE CASCADE
);


-- 5. habit_logs

CREATE TABLE habit_logs (
                            habit_log_id SERIAL PRIMARY KEY,
                            log_date DATE NOT NULL,
                            status VARCHAR(50),
                            xp_earned INT DEFAULT 0,
                            habit_id INT NOT NULL,

                            CONSTRAINT fk_habit
                                FOREIGN KEY (habit_id)
                                    REFERENCES habits(habit_id)
                                    ON DELETE CASCADE
);