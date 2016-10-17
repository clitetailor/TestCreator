CREATE TABLE subject (
	subjectId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    subjectName VARCHAR(200) NOT NULL
);
    
CREATE TABLE essayquestion (
	essayQuestionId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000) NOT NULL,
    subjectId INTEGER NOT NULL,
    level INTEGER NOT NULL,
    description VARCHAR(1000),
    answer VARCHAR(1000)
);

CREATE TABLE choicequestion (
	choiceQuestionId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000) NOT NULL,
    subjectId INTEGER NOT NULL,
    level INTEGER NOT NULL
);

CREATE TABLE choiceAnswer (
	choiceAnswerId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    choiceQuestionId INTEGER NOT NULL,
    content VARCHAR(1000) NOT NULL,
    trueFalse BOOLEAN NOT NULL
);

ALTER TABLE essayquestion
	ADD CONSTRAINT fk_essay_subject FOREIGN KEY (subjectId) 
    REFERENCES subject(subjectId)
    ON DELETE CASCADE;
    
ALTER TABLE choicequestion
	ADD CONSTRAINT fk_choice_subject FOREIGN KEY (subjectId)
    REFERENCES subject(subjectId)
    ON DELETE CASCADE;
    
ALTER TABLE choiceanswer 
	ADD CONSTRAINT fk_choiceanswer_question  FOREIGN KEY (choiceQuestionId)
    REFERENCES choicequestion(choiceQuestionId)
    ON DELETE CASCADE;