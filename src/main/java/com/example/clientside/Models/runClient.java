package com.example.clientside.Models;

import com.example.serverSide.HostManager;

public class runClient {
    public static void main(String[] args) {
        HostManager h=new HostManager(8080);
        boolean b=h.dictionaryLegal("Q,s1.txt,s2.txt,");
        //HostModeModel h=new HostModeModel();
      // h.dictionaryLegal()
    }
}
