import java.util.Scanner;

public class MTOMineSweeper {
    int satirSay,sutunSay,mayinSayisi ;
    int tarla[][];
    String kullaniciArayuz[][];

    public MTOMineSweeper(int satirSay, int sutunSay) {
        this.satirSay = satirSay;
        this.sutunSay = sutunSay;
    }
    public MTOMineSweeper() {
    }

    public void satirSayAl(){
        System.out.println("Sat�r say�s�n�z giriniz :");
        Scanner sc = new Scanner(System.in);
        this.satirSay = sc.nextInt();
    }
    public void sutunSayAl(){
        System.out.println("Sutun say�s�n�z giriniz :");
        Scanner sc = new Scanner(System.in);
        this.sutunSay = sc.nextInt();

    }

    public void run(){
        kuralYazdir();
        satirSayAl();
        sutunSayAl();
        tarlaOlustur();
        mayinYerlestir();
        //tarlaYazdir(); //kontrol ama�l� may�nlar� g�rmek i�in
        //System.out.println("-----------------------------");
        sayilariYerlestir();
        //tarlaYazdir(); //kontrol ama�l� tarlan�n oyun �nceki hali
        oyna();
    }

    public void kuralYazdir(){ // Oyun kurallar� yaz�lacak.
        System.out.println("Ho�geldiniz.");
        System.out.println("Kuralllar;"
                        +"\nSat�r ve s�tun de�erleri girip may�na yakalanmamal�s�n�z.");
    }

    public void tarlaOlustur(){ //bo� oyun tarlas� olu�turur.
        this.tarla = new int[this.satirSay][this.sutunSay];
        this.kullaniciArayuz = new String[this.satirSay][this.sutunSay];
        for (int i = 0; i < this.kullaniciArayuz.length; i++){
            for(int j = 0; j < this.kullaniciArayuz[0].length; j++ ){
                kullaniciArayuz[i][j] = "_";
            }
        }
    }

    public void mayinYerlestir(){ //may�nlar� rastgele yerle�tirir.
        this.mayinSayisi = (this.satirSay*this.sutunSay) /4;
        int temp = this.mayinSayisi;

        while (temp>0){
            int r = (int) (Math.random() * (satirSay*sutunSay)) ;
            for (int i = 0; i < this.tarla.length; i++){
                for(int j = 0; j < this.tarla[0].length; j++ ){
                    r--;
                    if(r==0 && tarla[i][j] != 9){
                        tarla[i][j] = 9;
                        temp --;
                    }
                }
            }

        }

    }

    public void sayilariYerlestir(){ //may�n �evresi
        for (int i = 0; i < this.tarla.length; i++){
            for(int j = 0; j < this.tarla[0].length; j++ ){
                if(!isNotMayin(i,j)){
                    ekle(i,j);
                }
            }
        }
    }

    public boolean isRow(int n){ // s�n�r kontrol�
        if(n >= 0 && n<this.tarla.length) return true;
        else return false;
    }
    public boolean isCol(int n){ // s�n�r kontrol�
        if(n >= 0 && n<this.tarla[0].length) return true;
        else return false;
    }
    public boolean isNotMayin(int i,int j){
        if(this.tarla[i][j] != 9){
            return true;
        }
        else return false;
    }
    public void ekle(int a, int b){ //may�n�n �evresine +1 ekler
        for(int i = a-1; i<=a+1; i++){
            for(int j = b-1; j<=b+1; j++){
                if(isRow(i) && isCol(j) && isNotMayin(i,j)){
                    this.tarla[i][j] ++;
                }
            }
        }
    }
    public void tarlaYazdir(){ //kontrol ama�l� tarlan�n oynanmadan �nceki hali
        for (int str[]: this.tarla){
            for (int j: str){
                if(j == 9) System.out.print("* ");
                else System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public void oyna(){
        Scanner sc = new Scanner(System.in);
        boolean game = true;
        while (game){
            yazdirKullaniciArayuz();
            System.out.print("Sat�r de�erini giriniz :"); int i = sc.nextInt();
            System.out.print("Sutun de�erini giriniz :"); int j = sc.nextInt();
            if(isRow(i) && isCol(j)){
                if(!isNotMayin(i,j)){
                    System.out.println("Game Over");
                    tarlaYazdir();
                    break;
                }
                else {
                    this.kullaniciArayuz[i][j] = Integer.toString(this.tarla[i][j]) ;
                    if(isWin()){
                        System.out.println("Tebrikler kazand�n�z.");
                        tarlaYazdir();
                        break;
                    }
                }
            }
            else {
                System.out.println("Oyun s�n�rlar� d���na ��kt�n�z l�tfen tekrar giriniz.");
            }
        }

    }

    public boolean isWin(){
        int count = 0;
        for(String[] str : kullaniciArayuz){
            for (String s : str){
                if(s == "_"){
                    count ++;
                }
            }
        }
        if (count == mayinSayisi){
            return true;
        }else return false;
    }
    public void yazdirKullaniciArayuz(){
        for (String str[]: this.kullaniciArayuz){
            for (String j: str){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


}