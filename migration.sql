CREATE TABLE essayquestion (
	essayQuestionId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    level INTEGER NOT NULL,
    description VARCHAR(1000),
    answer VARCHAR(1000)
);

CREATE TABLE choicequestion (
	choiceQuestionId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    level INTEGER NOT NULL
);

CREATE TABLE choiceAnswer (
	choiceAnswerId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    choiceQuestionId INTEGER NOT NULL,
    content VARCHAR(1000) NOT NULL,
    trueFalse BOOLEAN NOT NULL
);
    
ALTER TABLE choiceanswer 
	ADD CONSTRAINT fk_choiceanswer_question  FOREIGN KEY (choiceQuestionId)
    REFERENCES choicequestion(choiceQuestionId)
    ON DELETE CASCADE;