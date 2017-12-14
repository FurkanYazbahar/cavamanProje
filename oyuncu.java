/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

public class oyuncu {
    Scanner klavye = new Scanner(System.in);
    
    public char hangi_Harf;
    public boolean insan_Mi;
    public String kullanici_Adi;
    
    oyuncu(){
        insan_Mi = true;
        hangi_Harf = 'X';
    }
    oyuncu(boolean insanmi_Kontrolu){
        insan_Mi = insanmi_Kontrolu;
        hangi_Harf = (insanmi_Kontrolu) ? 'X' : 'O';
    }
    oyuncu(boolean insanmi_Kontrolu, char kr){
        insan_Mi = insanmi_Kontrolu;
        hangi_Harf = kr ;
    }
    public String bilgisayar_Hamlesi_Uret(int kenar){
        String rdm ;
        rdm = Integer.toString((int)(Math.random()* kenar)) + "," + Integer.toString((int)(Math.random()* kenar));
        return rdm;
    }
    String insan_Oyuncu_hamlesi_Kontrol(){
        System.out.println("Lütfen girilen kenar aralığında hamle yapınız : ");
        String hamle = klavye.nextLine();
        return hamle;
    }
    
}
