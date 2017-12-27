
create TABLE sticker

(    
   id INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   text VARCHAR(4096)    
);

create TABLE stickerwithcolor

(    
   id INT CONSTRAINT stickerwithcolor_foreignkey
	REFERENCES sticker ON DELETE CASCADE ON UPDATE RESTRICT,   
   red int, green int, blue int    
);

create TABLE stickerwithposition

(    
   id INT CONSTRAINT stickerwithposition_foreignkey
	REFERENCES sticker ON DELETE CASCADE ON UPDATE RESTRICT,   
   x int, y int    
);

create TABLE stickerwithsize

(    
   id INT CONSTRAINT stickerwithsize_foreignkey
	REFERENCES sticker ON DELETE CASCADE ON UPDATE RESTRICT,   
   width int, height int    
);

create TABLE stickerwithfont

(    
   id INT CONSTRAINT stickerwithfont_foreignkey
	REFERENCES sticker ON DELETE CASCADE ON UPDATE RESTRICT,   
   name varchar(256), style int, size int    
);

DROP TABLE TASKWITHCOLOR;
DROP TABLE TASKWITHPOSITION;
DROP TABLE sticker;
