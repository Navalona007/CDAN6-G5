# CDAN6-G5
>database efa dynamic, testeo aloha
>le modifier sur table otran misy tsy mety tsara ny synchronisation any miaraka amle champ de texte

TO DO LIST :
==INTERFACE 		[SO SO]
====LAYOUT 			[SO SO]
====CSS 			[SO SO]
======FONTAWESOME   	[OK]
======RESP.DESIGN		[NOT YET]
==AFFICHAGE 		[OK]
==AJOUT 			[OK]
====DATA BASE UPDATE	[OK]
//Solution : FileWriter (path, true) les données dans la base sont modifiées et non écrasées!!!//
==ANNULER 			[OK]
==SUPRIMMER 		[OK]
====DATA BASE UPDATE	[OK]
//Faire appel à saveToFile()//
==MODIFIER     		[OK]
====DATA BASE UPDATE	[OK]
==RECHERCHE MULTI. 	[NOT YET]

NEEDS :
	> batabase.txt should be always found even if the program is on another laptop
	>> Solution : relative path [Solved]

	> Refresh function OR button so the list would refresh after any add, delete or modification 
	>> Problèmes : l'id incrémenté à partir du dernier élément si un élément est supprimé, les autres id restentnt les mêmes alors que c'est déjà soustrait dans le total, redondance d'id !!!
	>> Solutions : vrai lastIndex +1 ???

	> The file is read in UTF_8 from ANSI and saved as UTF_8, then after reopening it's read again in UTF_8 with previous UTF_8 saved encoding so it's no more ANSI and the characters are read in UTF_8 for the second time so it becomes another character which has nothing to have with the previous ones!!!
	>> OutPutStreamWriter with BufferWriter [Solved]

	> First line always disappear
	>> Re-check ListInit or Insert it in Overwriting method;

	> Value must be editable directly on the tableviex'cells 
	>> check editable box in scene builder [no effect], mouse clicked == 2 times;
	>> Regarder les codes pour chaque colonne au lieu du tableau entier§!§!§!§!§!§

	>TableView Editable set genre and secteur columns as combobox
	>> col_genre <Student, ComboBox>; 
