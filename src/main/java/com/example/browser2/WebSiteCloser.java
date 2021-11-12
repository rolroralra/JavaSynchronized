package com.example.browser2;

public class WebSiteCloser implements Runnable {

    private final WebBrowser webBrowser;

    public WebSiteCloser(WebBrowser webBrowser) {
        this.webBrowser = webBrowser;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000);
                webBrowser.removeAndNotify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
