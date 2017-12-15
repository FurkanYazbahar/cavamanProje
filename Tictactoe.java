/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;


import java.util.Scanner;

public class Tictactoe {

    public static void main(String[] args) {
        int kenar ;
        int sira = 1;
        String deger,cpu;
        String isim;
        boolean berabere = false;
        boolean bayrak,kazandi;
        Scanner klavye = new Scanner(System.in);
        System.out.println("İsminizi giriniz : ");
        isim = klavye.nextLine();
        oyuncu insan = new oyuncu(true);
         insan.kullanici_Adi=isim;
        oyuncu bilgisayar = new oyuncu(false);
        
        do{
        System.out.println("Oyun Tahtasının boyutunu giriniz : ");
        kenar = klavye.nextInt();
        }while(!(kenar == 3 || kenar == 5 ||kenar == 7)); 
        oyunTahtasi tahta = new oyunTahtasi(kenar);
        tahta.tahtayi_Baslat();
        System.out.println("");
        tahta.oyun_Tahtasini_Yazdir();
            
        do{
            if(sira==1){
                do{
                deger = insan.insan_Oyuncu_hamlesi_Kontrol();
                bayrak = tahta.hamleyi_Yaz(deger,insan.hangi_Harf);
                 if(!bayrak)
                        System.out.println("Burası dolu ! Lütfen Tekrar giriş yapınız :");
                }while(!bayrak);
                tahta.oyun_Tahtasini_Yazdir();
                kazandi = tahta.kazanan(insan.hangi_Harf);  
                 if(kazandi && !berabere ){
                      System.out.println("Tebrikler Kazandınız");
                      System.exit(0);
                  }
                 if(berabere){
                     System.out.println("Berabere.");
                     System.exit(0);
                 }
            }
            else{
                System.out.println("Bilgisayar Oynuyor.");
                do{
                deger = bilgisayar.bilgisayar_Hamlesi_Uret(kenar);
                bayrak = tahta.hamleyi_Yaz(deger,bilgisayar.hangi_Harf);
                }while(!bayrak);
                tahta.oyun_Tahtasini_Yazdir();
                kazandi = tahta.kazanan(bilgisayar.hangi_Harf);
                  if(kazandi && !berabere ){
                      System.out.println("Bilgisayar Kazandı.");
                      System.exit(0);
                  }
                  if(berabere){
                     System.out.println("Berabere.");
                     System.exit(0);
                 }
            }
            
            if(sira==1)
                sira=2;
            else
                sira=1;
             
        }while(!kazandi);
    }
    
}
