/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

public class oyunTahtasi {
    
    public char[][] tahta ;
    public int kenar;
    
    oyunTahtasi(int kenar){
       tahta = new char[kenar][kenar];
       this.kenar = kenar;
       
    }
    oyunTahtasi(char[][] oyn_Tahtasi, int kenar){
        tahta = oyn_Tahtasi;
        this.kenar = kenar;
    }
    
//    public char[][] oyun_Tahtasini_Al(){
//        return 
//    }
    public void oyun_Tahtasini_Yazdir(){
        for(int m=0; m<4*kenar+3; m++)
        System.out.print("-");
        System.out.println("");
        for (int i = 0; i < kenar; i++){
            System.out.print(" | ");
            for (int j = 0; j < kenar; j++){
                System.out.print(tahta[i][j] + " | ");
            }
        System.out.println();
        for(int n=0;n<4*kenar+3;n++)
        System.out.print("-");
            System.out.println("");
        }
    }
    public void tahtayi_Baslat(){
        for(int i=0;i<kenar;i++)
            for(int j=0;j<kenar;j++)
                tahta[i][j] = '-';
    }
    boolean hamleyi_Yaz(String koordinat, char oyuncu){
        int satir;
        int sutun;
        String[] k = koordinat.split(",");
        satir = Integer.parseInt(k[0]);
        sutun = Integer.parseInt(k[1]);
        if(tahta[satir][sutun]=='-'&&tahta[satir][sutun]!='X'&&tahta[satir][sutun]!='O'){
            tahta[satir][sutun] = oyuncu;
            return true;
        }
        else
            return false;
    }
    boolean beraberlik_Kontrol(){
        for(int i=0;i<kenar;i++)
            for(int j=0;j<kenar;j++)
                if(tahta[i][j]=='-'&&tahta[i][j]!='X'&&tahta[i][j]!='O')
                    return false;         
        return true;        
    }
    boolean kazanan(char oyuncu){
         if(capraz_Bak(oyuncu))
             return true;
         else if(satir_Bak(oyuncu))
             return true;
         else if(sutun_Bak(oyuncu))
             return true;
         else
             return false;
    }
    boolean capraz_Bak(char oyuncu){
        int sayac=0;
        for(int i=0;i<kenar;i++)
            if(tahta[i][i]==oyuncu)
                sayac++;
        
        if(sayac==kenar)
            return true;
        sayac=0;
        for(int j=0;j<kenar;j++)
            if(tahta[j][kenar-1-j]==oyuncu)
                sayac++;
         if(sayac==kenar)
            return true;
         
         return false;
    }
    boolean satir_Bak(char oyuncu){
        int sayac=0;
        for(int i=0;i<kenar;i++){
            for(int j=0;j<kenar;j++){
                if(tahta[i][j]==oyuncu)
                    sayac++;
            }
            if(sayac==kenar)
                return true;
            else
                sayac=0;
        }
        return false;
    }
    boolean sutun_Bak(char oyuncu){
        int sayac=0;
        for(int i=0;i<kenar;i++){
            for(int j=0;j<kenar;j++){
                if(tahta[j][i]==oyuncu)
                    sayac++;
            }
            if(sayac==kenar)
                return true;
            else
                sayac=0;
        }
        return false;
    } 
}
