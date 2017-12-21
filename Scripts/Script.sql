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

delete FROM taskwithcolor

DELETE FROM TASK WHERE id > 1000

INSERT INTO TASKWITHCOLOR (id, red, green, blue) VALUES (501, 0, 0, 0)
(206,226,215)
update  TASKWITHCOLOR SET red = 206, green = 226, blue = 215 WHERE id = 501
INSERT into taskwithposition (id, x, y) VALUES (502, 500, 236)
INSERT INTO task (description) VALUES ('This is a task with a weird position')

DELETE FROM task;