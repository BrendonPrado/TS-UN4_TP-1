
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author brend
 */
public class codificador {
     public String codifica(String p) {
		String n_p = "";
		for (int i = 0; i < p.length(); i++) {
                    if((char) p.charAt(i)=='8'){
                        n_p+=(char)(p.charAt(i) - 7);
                        continue;
                    }
                    if((char) p.charAt(i)=='9'){ 		
                       n_p+=(char)(p.charAt(i)- 9);
                        continue;
                    }
                    if((char) p.charAt(i)=='7'){
                        n_p+=(char)(p.charAt(i) -5 );
                        continue;
                    }
                     if((char) p.charAt(i)=='z'||(char) p.charAt(i)=='Z'){
                        n_p+=(char)(p.charAt(i) - 25  );
                        continue;
                    } if((char) p.charAt(i)=='y'||(char) p.charAt(i)=='Y'){
                        n_p+=(char)(p.charAt(i) - 23  );
                        continue;
                    } if((char) p.charAt(i)=='x'||(char) p.charAt(i)=='X'){
                        n_p+=(char)(p.charAt(i) - 21  );
                        continue;
                    }
                     
			n_p+=(char)(p.charAt(i) + 3);
		}
                
		
		return n_p;
	}
	public String descodifica(String p) {
		String n_p = "";
                for (int i = 0; i < p.length(); i++) {
                    if((char) p.charAt(i)=='1'){
                        n_p+=(char)(p.charAt(i) + 7);
                        continue;
                    }
                    if((char) p.charAt(i)=='0'){ 		
                       n_p+=(char)(p.charAt(i)+ 9);
                        continue;
                    }
                    if((char) p.charAt(i)=='2'){
                        n_p+=(char)(p.charAt(i) +5 );
                        continue;
                    }
                     if((char) p.charAt(i)=='a'||(char) p.charAt(i)=='A'){
                        n_p+=(char)(p.charAt(i) + 25  );
                        continue;
                    } if((char) p.charAt(i)=='c'||(char) p.charAt(i)=='C'){
                        n_p+=(char)(p.charAt(i) + 23  );
                        continue;
                    } if((char) p.charAt(i)=='b'||(char) p.charAt(i)=='B'){
                        n_p+=(char)(p.charAt(i) + 21  );
                        continue;
                    }
			n_p+=(char)(p.charAt(i) - 3);
		}
		return n_p;
	}
}
