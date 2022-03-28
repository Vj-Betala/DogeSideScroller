public class mainDoge {
    public static void main(String[] args){
        dogeFrame a = new dogeFrame("");
        Thread t = new Thread(a.p);
        t.start();

    }
}
