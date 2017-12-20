create TABLE task

(    
   id INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   description VARCHAR(4096)    
);
COMMIT;

create TABLE taskwithcolor

(    
   id INT CONSTRAINT taskwithcolor_foreignkey
	REFERENCES task ON DELETE CASCADE ON UPDATE RESTRICT,   
   red int, green int, blue int    
);

create TABLE taskwithposition

(    
   id INT CONSTRAINT taskwithposition_foreignkey
	REFERENCES task ON DELETE CASCADE ON UPDATE RESTRICT,   
   x int, y int    
);

SELECT * FROM task
SELECT * FROM taskwithcolor
SELECT * FROM taskwithposition

rgb(230, 255, 230)
INSERT INTO TASKWITHCOLOR (id, red, green, blue) VALUES (501, 230, 255, 230)
INSERT into taskwithposition (id, x, y) VALUES (502, 500, 236)
INSERT INTO task (description) VALUES ('This is a task with a weird position')

DELETE FROM task;