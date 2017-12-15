import java.util.Scanner;
import java.util.*;
import java.io.*;

public class TicTacToe{
	public static void main(String[] args) {
		Oyuncu oyuncuBir = new Oyuncu(true,  'X');
		Oyuncu oyuncuIki = new Oyuncu(false, 'O', "CPU");

		int tahtaKenarBoyutu = 3;
		char[][] arr = {
			{'X', 'O', 'X'},
			{'X', 'O', 'X'},
			{'O', 'X', 'O'}
		};

		OyunTahtasi tahta = new OyunTahtasi();
		int kere = (tahta.kenar() * tahta.kenar()) / 2;
		String hamle;

		for (int i = 0; i < kere ; i++){
			tahta.oyunTahtasiniYazdir();
			hamle = oyuncuBir.oyuncununHamlesiniAl(tahta, tahta.kenar());
			tahta.hamleyiYaz(hamle, oyuncuBir.karakteriAl());
			tahta.hamleyiSil(hamle);
			//oyuncuBir.hamleUygula(tahta, tahta.kenar(), hamle);

			tahta.oyunTahtasiniYazdir();
			hamle = oyuncuIki.oyuncununHamlesiniAl(tahta, tahta.kenar());			
			tahta.hamleyiYaz(hamle, oyuncuIki.karakteriAl());
			tahta.hamleyiSil(hamle);

//			tahta.hamleyiYaz(oyuncuBir.oyuncununHamlesiniAl(tahta, tahta.kenar()), oyuncuBir.karakteriAl());
//			tahta.hamleyiYaz(oyuncuIki.oyuncununHamlesiniAl(tahta, tahta.kenar()), oyuncuIki.karakteriAl());
		}


		System.out.println(tahta.oyunDurumu());
		tahta.oyunTahtasiniYazdir();
	}

/*
	public void dosyayaKaydet(String sonDurum) {
		File dosya = new File("TicTacToeKayıt.txt");

		if(dosya.exists() && !dosya.isDirectory()) { 		
			if (dosya.createNewFile())
				System.out.println("dosya oluşturuldu!");
			else
				System.out.println("dosya oluşturuldu!");
		}

		PrintWriter tuyKalem = new PrintWriter("TicTacToeKayıt.txt", "UTF-8");
		tuyKalem.printf("%s", sonDurum);
		tuyKalem.close();
		System.out.println("dosya yazıldı!");
	}
*/
}
class Zipper{
	// tayaksZipper fonksiyonuyla sıkıştırılan sayıları geri açar
	static public int[] whereIsWaldo(int sayi, int ozLimit) {
		int[] satirSutun = new int[2];
		int limit = ozLimit + 1;

		satirSutun[0] = sayi % limit;
		satirSutun[1] = (sayi - satirSutun[0]) / limit;
		return satirSutun;
	} 

	// verilen satır ve sutunları birleştirerek bir int elde eder
	static public int tayaksZipper(int satir, int sutun, int limit) {
		return satir + sutun * (limit + 1);
	}
}
class OyunTahtasi{

	private int kalanHamle;

	// oyun tahtasını tutar
	private char[][] tahta;

	// tahta boyutunu tutar
	private int tahtaKenarBoyutu;

	// olasi hamlelerin tutulduğu liste
	//private List<Integer> olasiHamleler;
	private ArrayList<Integer> olasiHamleler;

	public int kenar(){
		return tahtaKenarBoyutu;
	}

	public int hamleToZipcode(String hamle) {
		int satir, sutun;
		satir = Character.getNumericValue(hamle.charAt(0));
		sutun = Character.getNumericValue(hamle.charAt(2));

		return Zipper.tayaksZipper(satir, sutun, tahtaKenarBoyutu);

	}

	// olası olarak bir eleman çeker ve diziden siler
	public int rastsalHamleCek() {
		int rastSayi, eleman = -1;
		rastSayi = olasiHamleler.size();
		rastSayi = (int)(Math.random() * rastSayi + 1);

		eleman = olasiHamleler.get(rastSayi);
		olasiHamleler.remove(rastSayi);

		return eleman;
	}

	// NOT: try catch eklenip kontrol konulacak
	public boolean hamleyiSil(int hamle) {
		olasiHamleler.remove(hamle);
		return true;
	}
	public boolean hamleyiSil(String hamle) {
		return hamleyiSil(this.hamleToZipcode(hamle));
	}

	// Verilen ziplenmiş hareketi olasi hamleler arasinda arar. varsa true döner
	public boolean olasiHamleVarMi(int hamle) {
		return olasiHamleler.contains(hamle);
	}

	// butun hareketleri zipleyip bir arrayliste atar
	private void butunOlasiHareketleriHesapla(int limit) {
		olasiHamleler = new ArrayList<Integer>(limit);
		for (int i = 0; i < tahtaKenarBoyutu; i++)
			for (int j = 0; j < tahtaKenarBoyutu; j++)
				olasiHamleler.add(Zipper.tayaksZipper(i, j, tahtaKenarBoyutu));
	}

	// oyun tahtasını, klavyeden aldığıargümanlarla oluşturur
	public OyunTahtasi() {
		tahta = oyunTahtasiniAl();
		butunOlasiHareketleriHesapla(tahtaKenarBoyutu * tahtaKenarBoyutu);
	}

	// mevcut oyuntahtasını alır
	public OyunTahtasi(char[][] oyunTahtasi)
	{
		tahta = oyunTahtasi;
		tahtaKenarBoyutu = oyunTahtasi[0].length;
		butunOlasiHareketleriHesapla(tahtaKenarBoyutu * tahtaKenarBoyutu);
	}

	// oyun tahtasını alır
	private char[][] oyunTahtasiniAl()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Tahta boyutu giriniz: ");
		int n = keyboard.nextInt();
		tahtaKenarBoyutu = n;
		char[][] yerel_tahta = new char[n][n];

		return yerel_tahta;
	}

	// oyun tahtasının bütün elemanlarını temizler
	public void oyunTahtasiniTemizle() {
		for (int i = 0; i < tahtaKenarBoyutu; i++)
			for (int j = 0; j < tahtaKenarBoyutu; j++)
				tahta[i][j] = 0;
	}

	public String oyunDurumu() {
		String out = "";
		for (int i = 0; i < tahtaKenarBoyutu; i++)
			for (int j = 0; j < tahtaKenarBoyutu; j++)
				out += (int)tahta[i][j];
		return out;
	}
	// oyuncu tahtasını ekrana bastırır
	public void oyunTahtasiniYazdir() {
		char c;
		this.cizgiCek();
		for (int i = 0; i < tahtaKenarBoyutu; i++) {
			System.out.print((i+1) + " [ ");
			for (int j = 0; j < tahtaKenarBoyutu; j++){
				c = tahta[i][j];
				if (c == 0)
					System.out.printf(" - ");
				else
					System.out.printf(" %c ",c);
			}

			System.out.println(" ]");
		}
		this.cizgiCek();
	}

	// oyun tahtasini ekrana basan fonksiyon için çizgi çeker
	private void cizgiCek() {
		int sayac = tahtaKenarBoyutu * 3 + 4;
		System.out.print("  ");

		for (int i = 0; i < sayac; i++)
			System.out.print('-');

		System.out.println("");
	}

	// girilen alan boş mu diye kontrol eder. boşsa true döner
	public boolean hamleyiYaz(String koordinat, char oyuncu) {
		int satir, sutun;
		String[] k = koordinat.split(",");
		satir = Integer.parseInt(k[0]);
		sutun = Integer.parseInt(k[1]);
		tahta[satir][sutun] = oyuncu;
		return true;
	}
/*
	// indisler 0'dan itibaren hesaplanır
	// bulursa indisi, bulamazsa -1 döner
	private int yatayAra(int satir, char aranan) {
		satir = (satir > tahtaKenarBoyutu) ? tahtaKenarBoyutu : satir; 
		for (int i = 0; i <= satir; i++)
			if(this.tahta[satir][i] == aranan)
				return i;

		return -1;
	}

	// bulursa indisi, bulamazsa -1 döner
	private int boyunaAra(int sutun, char aranan) {
		sutun = (sutun > tahtaKenarBoyutu)? tahtaKenarBoyutu - 1 : sutun;
		for (int i = 0; i <= tahtaKenarBoyutu; i++)
			if(this.tahta[i][sutun] == aranan)
				return i;
		
		return -1;
	}
*/
	// oyuncu oyunu kazandıysa true, aksi halde false döner
	public boolean kazanan(char oyuncu) {
		return true;
	}

	// beraberse true aksi halde false döner
	public boolean beraberlikKontrol() {
		for (int i = 0; i < tahtaKenarBoyutu; i++)
			for (int j = 0; j < tahtaKenarBoyutu; j++)
				if(tahta[i][j] == 0)
					return false;
		return true;  
	}

}

class Oyuncu {

	private boolean siraOyuncudaMi = false; 
	private char oyuncuHarfi  = 'X';
	private String oyuncuIsmi = "TicTocTaYaK";
	private boolean insanMi   = true;
	private Scanner klavye    = new Scanner(System.in);

	// değerleri öntanımlı verdiğimizden buna ihtiyaç yok
	public Oyuncu() {
	}

	public Oyuncu(boolean insanmiKontrolu) {
		if(!insanmiKontrolu) {
			insanMi = false;
			oyuncuHarfi = 'X';
			oyuncuIsmi = "CPU";
 		}
	}

	public Oyuncu(boolean insanmiKontrolu, char kr) {
		insanMi = insanmiKontrolu;
		oyuncuHarfi = kr;
	}

	public Oyuncu(boolean insanmiKontrolu, char kr, String isim) {
		insanMi = insanmiKontrolu;
		oyuncuHarfi = kr;
		oyuncuIsmi = isim;
	}

	// oyuncunun oynadığı karakter değerini döner
	public char karakteriAl() {
		return oyuncuHarfi;
	}

	// oyun turunun kimde olduğunu döndürür
	public boolean oyuncuTurunuAl() {
		return siraOyuncudaMi;
	}

	// mevcut oyuncunun ismini döner
	public String oyuncuIsminiAl() {
		return oyuncuIsmi;
	}

	// mevcut oyuncunun hamlesini alır
	public String oyuncununHamlesiniAl(OyunTahtasi tahta, int ozLimit) {
		String hamle = "";
		boolean gecerliHamleMi = false;

		while(!gecerliHamleMi && hamle == "") {	
			if (insanMi) {	
				hamle = insanOyuncuHamlesiniAl();
				System.out.printf("%s: %s oynadı...\n", oyuncuIsminiAl(), hamle);
				hamle = insanOyuncuHamlesiniKontrol(tahta, ozLimit, hamle);
			} else {
				hamle = bilgisayarHamlesiUret(tahta, ozLimit);
				System.out.printf("%s: %s oynadı...\n", oyuncuIsminiAl(), hamle);
				gecerliHamleMi = true;
			}

			// insansa ve hamlesi geçersizse uyar
			if(!gecerliHamleMi && insanMi && hamle == "")
				System.out.println("Girdiğiniz hamle geçersiz!!!");
			else
				gecerliHamleMi = true;

		}
		return hamle;
	}
/*
	// oyuncu için verilen hamleyi uygular. Eğer geçersiz hamleyse false döner
	public boolean hamleUygula(OyunTahtasi tahta, int ozLimit, String hamle) {
		hamle = insanOyuncuHamlesiniKontrol(tahta, ozLimit, hamle);

		if (hamle == ""){
			System.out.println("Verilen ölçütlerde bir hamle değil!!!");
			return false;
		}

		int zipcode = gecerliHamleVarMi(tahta, ozLimit, hamle);

		if (zipcode == -1){
			System.out.println("Bu hamleuygulanamaz!!!");
			return false;
		}

		tahta.hamleyiYaz(hamle, karakteriAl());
		tahta.hamleyiSil(hamle);
		return true;
	}
*/
	
	// kullanıcıdan bir hale bekler
	private String insanOyuncuHamlesiniAl() {
		System.out.println("Hamle yapınız: \"örn:2,3\" ");
		return klavye.nextLine();
	}

	// gecerli hamle var mi diye bakar. varsa zipcodunu döner
	// yoksa -1 döner
	private int gecerliHamleVarMi(OyunTahtasi tahta, int ozLimit, String hamle) {
		int zipcode = tahta.hamleToZipcode(hamle);
		
		return (tahta.olasiHamleVarMi(zipcode)) ? zipcode : -1;
	}

	// kullanıcıdan alınan string kontrol edilir
	// stderr e basılacaklar
	private String insanOyuncuHamlesiniKontrol(OyunTahtasi tahta, int ozLimit, String hamle) {
		if (hamle.length() > 3){
			System.out.printf("Hamle'nin uzunluğu %d. OLması gerken 3!!\n", hamle.length());
			return "";
		}
		else if (hamle.charAt(1) != ','){
			System.out.println("İkinci eleman virgül değil!!!");
			System.out.printf("eleman: %c\n", hamle.charAt(1));
			return "";
		}
		else if(!Character.isDigit(hamle.charAt(0)) || !Character.isDigit(hamle.charAt(2))){
			System.out.println("1. ve 3. elemanlar sayı değil");
			System.out.printf("girilen: %s -> 1: %c, 2 : %c\n", hamle, hamle.charAt(0), hamle.charAt(2));
			return "";
		}

		return hamle;
	}

	private String bilgisayarHamlesiUret(OyunTahtasi tahta, int ozLimit) {
		int sayi = tahta.rastsalHamleCek();
		int[] sonuc = Zipper.whereIsWaldo(sayi, ozLimit);
		return Integer.toString(sonuc[0]) + "," + Integer.toString(sonuc[1]);
	}
}
