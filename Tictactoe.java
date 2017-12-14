/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.Console;
import java.util.Scanner;

public class Tictactoe {

    public static void main(String[] args) {
        int kenar ;
        int sira = 1;
        Scanner klavye = new Scanner(System.in);
        oyuncu furkan = new oyuncu(true);
        oyuncu bilgisayar = new oyuncu(false);
        do{
        System.out.println("Oyun Tahtasının boyutunu giriniz : ");
        kenar = klavye.nextInt();
        }while(!(kenar == 3 || kenar == 5 ||kenar == 7)); 
        oyunTahtasi tahta = new oyunTahtasi(kenar);
        tahta.tahtayi_Baslat();
        System.out.println("");
        tahta.oyun_Tahtasini_Yazdir();
        String b = furkan.insan_Oyuncu_hamlesi_Kontrol();
        String a = bilgisayar.bilgisayar_Hamlesi_Uret(kenar);
        boolean k = tahta.hamleyi_Yaz(b,furkan.hangi_Harf);
        boolean c = tahta.hamleyi_Yaz(a,bilgisayar.hangi_Harf);
        
         tahta.oyun_Tahtasini_Yazdir();
         System.out.println(tahta.beraberlik_Kontrol());
         System.out.println(tahta.beraberlik_Kontrol());
         System.out.println(bilgisayar.hangi_Harf);
    }
    
}
