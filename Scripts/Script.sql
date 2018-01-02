
create TABLE note

(    
   id INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   text VARCHAR(4096)    
);

create table envelope (    
   id INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   note INT CONSTRAINT note_foreignkey
	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT   
);

create TABLE envelopewithcolor

(    
   id INT CONSTRAINT envelopewithcolor_foreignkey
	REFERENCES envelope ON DELETE CASCADE ON UPDATE RESTRICT,   
   red int, green int, blue int    
);

create TABLE envelopewithposition

(    
   id INT CONSTRAINT envelopewithposition_foreignkey
	REFERENCES envelope ON DELETE CASCADE ON UPDATE RESTRICT,   
   x int, y int    
);

create TABLE envelopewithsize

(    
   id INT CONSTRAINT envelopewithsize_foreignkey
	REFERENCES envelope ON DELETE CASCADE ON UPDATE RESTRICT,   
   width int, height int    
);

create TABLE envelopewithfont

(    
   id INT CONSTRAINT envelopewithfont_foreignkey
	REFERENCES envelope ON DELETE CASCADE ON UPDATE RESTRICT,   
   name varchar(256), style int, size int    
);

delete from note
delete from  envelopewithcolor