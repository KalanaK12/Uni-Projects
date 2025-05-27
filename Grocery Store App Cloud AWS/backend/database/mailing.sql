-- Active: 1693905023571@@127.0.0.1@3306
DROP TABLE IF EXISTS "MailingList";

CREATE TABLE IF NOT EXISTS MailingList(
    ml_email VARCHAR(255) NOT NULL,
    PRIMARY KEY (ml_email)
);

-- Insert some emails
INSERT OR IGNORE INTO MailingList(ml_email)
VALUES ("s3948583@student.rmit.edu.au");

INSERT OR IGNORE INTO MailingList(ml_email)
VALUES ("s3948555@student.rmit.edu.au");

INSERT OR IGNORE INTO MailingList(ml_email)
VALUES ("s3944502@student.rmit.edu.au");

INSERT OR IGNORE INTO MailingList(ml_email)
VALUES ("s3948768@student.rmit.edu.au");

INSERT OR IGNORE INTO MailingList(ml_email)
VALUES ("s1234567@student.rmit.edu.au");
