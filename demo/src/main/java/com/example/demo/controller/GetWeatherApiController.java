package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.commons.text.StringEscapeUtils;

@RestController
@RequestMapping("external-api")
public class GetWeatherApiController {
  
  // "http://localhost:8080/api/weather/tokyo" でアクセス。
  /**
  * @return 東京の天気情報
  * 
  * @see <a href="https://weather.tsukumijima.net/">天気予報 API（livedoor 天気互換）</a>
  * 2020年7月、livedoor 天気がサービス終了となったため、代替APIを利用。
  */
  @RequestMapping(value="weather/tokyo"
          , produces=MediaType.APPLICATION_JSON_VALUE
          , method=RequestMethod.GET)
  private String call() {

    RestTemplate rest = new RestTemplate();

    final String cityCode = "130010"; // 東京のCityCode
    final String endpoint = "https://weather.tsukumijima.net/api/forecast";

    final String url = endpoint + "?city=" + cityCode;

    // 直接Beanクラスにマップ出来るけど今回はめんどくさいのでStringで。
    ResponseEntity<String> response = rest.getForEntity(url, String.class);

    String json = response.getBody();

    return decode(json);
  }

  // いわゆる日本語の２バイト文字がunicodeエスケープされてるので解除。
  private static String decode(String string) {
    return StringEscapeUtils.unescapeJava(string);  
  }
}
