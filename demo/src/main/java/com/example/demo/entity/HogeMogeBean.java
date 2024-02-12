package com.example.demo.entity;

import lombok.Data;

@Data
public class HogeMogeBean {
  
  private String hoge;
  private int moge;

  public HogeMogeBean(String hoge, int moge) {
    this.hoge = hoge;
    this.moge = moge;
  } 

}
