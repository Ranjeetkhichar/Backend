package org.example.ConcurrencyPlayground;

public class PrintNos implements Runnable{
    private Integer no;
    public PrintNos(Integer no){
        this.no = no;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            System.out.println("PrintNo is " + no + " and is printed by thread " + Thread.currentThread().getName());
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
