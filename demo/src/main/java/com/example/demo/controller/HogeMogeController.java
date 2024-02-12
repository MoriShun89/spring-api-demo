package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.HogeMogeBean;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/json/api")
public class HogeMogeController {
  
  @RequestMapping("hogemoge")
  public HogeMogeBean hogemoge() {
    return new HogeMogeBean( "ほげ", 1234 );
  }

  /*
   * produces属性には、このAPIが返すデータのメディアタイプを指定しています。
   * この場合、JSON形式のデータを返すことを示しています。
   */
  @RequestMapping(
        value = "hogemoge2", 
        produces = MediaType.APPLICATION_JSON_VALUE)
  public String string() throws Exception {
    HogeMogeBean bean = new HogeMogeBean("もげ", 297);

    /*
     * HogeMogeBeanクラスのインスタンスを作成し、ObjectMapperを使用してJSON形式の文字列に変換しています。
     * ObjectMapperは、JavaオブジェクトをJSON形式の文字列に変換するためのライブラリです。
     * writeValueAsString()メソッドを使用することで、HogeMogeBeanオブジェクトをJSON形式の文字列に変換できます。
     */
    String json = new ObjectMapper().writeValueAsString(bean);
    return json;
  }

  // マップを返すAPI
  @RequestMapping("hogemoge3")
  public Map<String, Object> map() {
    Map<String, Object> map = new HashMap<>();
    map.put( "hoge", "ぴよ" );
    map.put( "moge", 999 );
    return map;
  }

  // ファイルにアクセスするAPI
  @RequestMapping( 
        value = "hogemoge4", 
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
  public Resource file() {
    return new FileSystemResource( new File("/Users/moriokashun/Documents/【1430森岡駿】【認定証】OSS-DB Silver.pdf") );
  }
}
