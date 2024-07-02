CREATE TABLE user (
    userNo INT AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    securityQuestion INT NOT NULL,
    securityAnswer VARCHAR(255) NOT NULL,
    isActive BOOLEAN DEFAULT TRUE,
    type INT DEFAULT 1,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category (
    categoryNo INT AUTO_INCREMENT PRIMARY KEY,
    categoryTitle VARCHAR(255) NOT NULL,
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE note (
    noteNo INT AUTO_INCREMENT PRIMARY KEY,
    noteTitle VARCHAR(255) NOT NULL,
    noteContent TEXT NOT NULL,
    noteHint VARCHAR(255) NOT NULL,
    noteAnswer TEXT NOT NULL,
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE reply (
    replyNo INT AUTO_INCREMENT PRIMARY KEY,
    replyContent VARCHAR(255) NOT NULL,
    parentReply INT NOT NULL,
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (parentReply) REFERENCES reply(replyNo)
);

CREATE TABLE announcement (
    announcementNo INT AUTO_INCREMENT PRIMARY KEY,
    announcementTitle VARCHAR(255) NOT NULL,
    announcementContent TEXT NOT NULL,
    schedule TIMESTAMP NOT NULL,
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE file (
    fileNo INT AUTO_INCREMENT PRIMARY KEY,
    savedName VARCHAR(255) NOT NULL,
    uploadName VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE alarm (
    alarmNo INT AUTO_INCREMENT PRIMARY KEY,
    alarmNote VARCHAR(255) NOT NULL,
    alarmType INT NOT NULL,
    isRead BOOLEAN DEFAULT FALSE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE report (
    reportNo INT AUTO_INCREMENT PRIMARY KEY,
    reportType INT NOT NULL,
    reportNote VARCHAR(255) NOT NULL,
    solvedType INT DEFAULT 0,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    solvedAt TIMESTAMP NOT NULL
);

CREATE TABLE favorite (
    favoriteNo INT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE noteView (
    noteViewNo INT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categoryView (
    categoryViewNo INT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE noteCategory (
    categoryNo INT NOT NULL,
    noteNo INT NOT NULL,
    PRIMARY KEY (categoryNo, noteNo),
    FOREIGN KEY (categoryNo) REFERENCES category(categoryNo),
    FOREIGN KEY (noteNo) REFERENCES note(noteNo)
);

CREATE TABLE noteUser (
    userNo INT NOT NULL,
    noteNo INT NOT NULL,
    PRIMARY KEY (userNo, noteNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (noteNo) REFERENCES note(noteNo)
);

CREATE TABLE noteReply (
    noteNo INT NOT NULL,
    replyNo INT NOT NULL,
    PRIMARY KEY (noteNo, replyNo),
    FOREIGN KEY (noteNo) REFERENCES note(noteNo),
    FOREIGN KEY (replyNo) REFERENCES reply(replyNo)
);

CREATE TABLE replyUser (
    userNo INT NOT NULL,
    replyNo INT NOT NULL,
    PRIMARY KEY (userNo, replyNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (replyNo) REFERENCES reply(replyNo)
);

CREATE TABLE announcementUser (
    announcementNo INT NOT NULL,
    userNo INT NOT NULL,
    PRIMARY KEY (announcementNo, userNo),
    FOREIGN KEY (announcementNo) REFERENCES announcement(announcementNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo)
);

CREATE TABLE fileUser (
    userNo INT NOT NULL,
    fileNo INT NOT NULL,
    PRIMARY KEY (userNo, fileNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (fileNo) REFERENCES file(fileNo)
);

CREATE TABLE fileNote (
    noteNo INT NOT NULL,
    fileNo INT NOT NULL,
    PRIMARY KEY (noteNo, fileNo),
    FOREIGN KEY (noteNo) REFERENCES note(noteNo),
    FOREIGN KEY (fileNo) REFERENCES file(fileNo)
);

CREATE TABLE fileAnnouncement (
    announcementNo INT NOT NULL,
    fileNo INT NOT NULL,
    PRIMARY KEY (announcementNo, fileNo),
    FOREIGN KEY (announcementNo) REFERENCES announcement(announcementNo),
    FOREIGN KEY (fileNo) REFERENCES file(fileNo)
);

CREATE TABLE alarmRelation (
    alarmNo INT NOT NULL,
    userNo INT NOT NULL,
    makerNo INT NOT NULL,
    PRIMARY KEY (alarmNo, userNo, makerNo),
    FOREIGN KEY (alarmNo) REFERENCES alarm(alarmNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (makerNo) REFERENCES user(userNo)
);

CREATE TABLE reportManagement (
    userNo INT NOT NULL,
    managerNo INT NOT NULL,
    solverNo INT NOT NULL,
    PRIMARY KEY (userNo, managerNo, solverNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (managerNo) REFERENCES user(userNo),
    FOREIGN KEY (solverNo) REFERENCES user(userNo)
);

CREATE TABLE reportCategory (
    userNo INT NOT NULL,
    categoryNo INT NOT NULL,
    reportNo INT NOT NULL,
    PRIMARY KEY (userNo, categoryNo, reportNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (categoryNo) REFERENCES category(categoryNo),
    FOREIGN KEY (reportNo) REFERENCES report(reportNo)
);

CREATE TABLE reportNote (
    userNo INT NOT NULL,
    noteNo INT NOT NULL,
    reportNo INT NOT NULL,
    PRIMARY KEY (userNo, noteNo, reportNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (noteNo) REFERENCES note(noteNo),
    FOREIGN KEY (reportNo) REFERENCES report(reportNo)
);

CREATE TABLE reportReply (
    userNo INT NOT NULL,
    replyNo INT NOT NULL,
    reportNo INT NOT NULL,
    PRIMARY KEY (userNo, replyNo, reportNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (replyNo) REFERENCES reply(replyNo),
    FOREIGN KEY (reportNo) REFERENCES report(reportNo)
);

CREATE TABLE favoriteCategory (
    userNo INT NOT NULL,
    categoryNo INT NOT NULL,
    favoriteNo INT NOT NULL,
    PRIMARY KEY (userNo, categoryNo, favoriteNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (categoryNo) REFERENCES category(categoryNo),
    FOREIGN KEY (favoriteNo) REFERENCES favorite(favoriteNo)
);

CREATE TABLE favoriteNote (
    userNo INT NOT NULL,
    noteNo INT NOT NULL,
    favoriteNo INT NOT NULL,
    PRIMARY KEY (userNo, noteNo, favoriteNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (noteNo) REFERENCES note(noteNo),
    FOREIGN KEY (favoriteNo) REFERENCES favorite(favoriteNo)
);

CREATE TABLE viewUserNote (
    userNo INT NOT NULL,
    noteNo INT NOT NULL,
    noteViewNo INT NOT NULL,
    PRIMARY KEY (userNo, noteNo, noteViewNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (noteNo) REFERENCES note(noteNo),
    FOREIGN KEY (noteViewNo) REFERENCES noteView(noteViewNo)
);

CREATE TABLE viewUserCategory (
    userNo INT NOT NULL,
    categoryNo INT NOT NULL,
    categoryViewNo INT NOT NULL,
    PRIMARY KEY (userNo, categoryNo, categoryViewNo),
    FOREIGN KEY (userNo) REFERENCES user(userNo),
    FOREIGN KEY (categoryNo) REFERENCES category(categoryNo),
    FOREIGN KEY (categoryViewNo) REFERENCES categoryView(categoryViewNo)
);
