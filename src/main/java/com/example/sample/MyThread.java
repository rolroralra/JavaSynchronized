package com.example.sample;

public class MyThread implements Runnable{
    static final int RUNNING = 0;
    static final int SUSPENDED = 1;
    static final int STOPPED = 2;

    private int state = RUNNING;
    Thread th;

    MyThread(String name){
        th = new Thread(this, name); /// Thread(Runnable r, String name)
    }

    public void run(){
        String name = Thread.currentThread().getName();

        while(true){
            System.out.println(name);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            // state가 STOPPED이면 checkState가 true를 변환해서 while문을 벗어나게 됩니다.
            if(checkState()){
                break;
            }
        }
    }

    public synchronized void setState(int state){
        this.state = state;

        // state가 SUSPENDED였다가 RUNNING으로 변경되면, notify()를 호출합니다.
        if(state == RUNNING){
            notify();
        }else{
            th.interrupt();
        }
    }

    public synchronized boolean checkState(){
        // state가 SUSPENDED면 wait를 호출해서 쓰레드를 대기상태로 만듬
        while(state == SUSPENDED){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        // state가 STOPPED이면 true를, 그 외는 false를 리턴합니다.
        return state == STOPPED;
    }

    public void suspend(){
        System.out.printf("%s SUSPEND%n", th);
        setState(SUSPENDED);
    }
    public void resume(){
        System.out.printf("%s RESUME%n", th);
        setState(RUNNING);
    }
    public void stop(){
        System.out.printf("%s STOP%n", th);
        setState(STOPPED);
    }
    public void start(){
        System.out.printf("%s START%n", th);
        th.start();
    }
}
