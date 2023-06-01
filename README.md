David Mihalcenco 331CB
    Tema2
    
    Fisirul Tema2 :
        Extrag din argumente folderul si numarul de threduri
        citest din fisierul order.txt, creez un semafor,care v-a 
        regla numarul de threduri care se executa, si pornesc thredurile.
    
    Fisierul Orders :
        Extrag din linii id-ul unui cumparator, si numarul de produse
        deschid fisierul order products. Si creez atatea threduri cate 
        produse am, datorica folosirii semaforului, toate aceste treduri 
        creeate nu vor rula odata, dar vor astepta cand semaforul le v-a da 
        voie, adica semaforul creeat initial are ca numar de threduri, numarul
        total admisibil de threduri, si la moment nu se vor executa mai multe
        decat este admisibil.
        Daca numarul de produse nu e zero atunci in orders out scriu linia si 
        shipped la urma.
    
    Fisierul Order_Products:
        In Orders Products fac verificarea fiecarei linii, si ma uit ce produse
        sunt destinate cumparatorului. Si scriu in fisierul 
        orders_products_out linia si shipped la final.