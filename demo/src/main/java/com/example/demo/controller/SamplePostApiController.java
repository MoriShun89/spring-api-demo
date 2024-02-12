package com.example.demo.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post-api")
public class SamplePostApiController {
  
  // 後述する幾つかの差し替え実装は省略。（詳しくはこの後の「RestTemplateラッパー三点セット」のあたりを参照）
  private final RestTemplate rest = new RestTemplate();

  /**
  * POST実装例.
  * 
  * 指定したエンドポイントに対して {@code json} データをPOSTし、結果を返す。
  * 
  * @param url     エンドポイント
  * @param headers リクエストヘッダ
  * @param json    送信するJSON文字列
  * @return 正常に通信出来た場合はレスポンスのJSON文字列を、<br>
  *         正常に通信出来なかった場合は {@code null} を返す。 
  */
  public String post(String url, Map<String, String> headers, String json) {

    /*
     * RequestEntityクラスのpost()メソッドを使用して、POSTリクエストを構築するためのBodyBuilderを作成しています。
     * uri(url)は、指定されたURLを元にjava.net.URIオブジェクトを作成します。
     */
    RequestEntity.BodyBuilder builder = RequestEntity.post( uri( url ) );

    for ( String name : headers.keySet() ) {
      String header = headers.get( name );
      builder.header( name, header );
    }

    /*
     * contentType()メソッドを使用して、リクエストのコンテンツタイプをJSONとして指定し、
     * body()メソッドを使用してJSONデータをリクエストボディに設定します。
     */
    RequestEntity<String> request = builder
            .contentType( MediaType.APPLICATION_JSON )
            .body( json );

    /*
     * RestTemplateのexchange()メソッドを使用して、作成したリクエストを送信し、レスポンスを取得します。
     * 取得したレスポンスはResponseEntityオブジェクトとして返されます。
     */
    ResponseEntity<String> response = this.rest.exchange( 
            request, 
            String.class );

    /*
     * レスポンスのステータスコードが2xx（成功）であれば、データの内容を取得して返します。
     * それ以外の場合は、nullを返します。
     */
    return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
  }

  private static final URI uri( String url ) {
    try {
        return new URI( url );
    }
    // 検査例外はうざいのでランタイム例外でラップして再スロー。
    catch ( Exception ex ) {
        throw new RuntimeException( ex );
    }
  }

  @PostMapping("send")
  public String sendData() {
    String url = "https://example.com/api/endpoint";
    Map<String, String> headers = new HashMap<>();
    headers.put("Authorization", "Bearer token123");
    String json = "{\"key\": \"value\"}";

    return post(url, headers, json);
  }
}
