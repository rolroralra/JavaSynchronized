package com.example.browser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Computer {

    public static void main(String[] args) {
        final WebBrowser webBrowser = new WebBrowser(5);

        final Thread threadA = new Thread(new WebSite("Google", webBrowser));
        final Thread threadB = new Thread(new WebSite("Naver", webBrowser));
        final Thread threadC = new Thread(new WebSite("Daum", webBrowser));
        final Thread threadD = new Thread(new WebSite("Samsung", webBrowser));

        threadA.start();
        threadB.start();
        threadC.start();
    }
}