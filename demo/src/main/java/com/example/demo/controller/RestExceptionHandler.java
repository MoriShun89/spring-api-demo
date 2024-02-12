package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.mapper.JsonMapper;

/*
 * コントローラー横断でエラーをハンドリングする。
 * @RestControllerAdviceアノテーションを使用しているため、
 * 全ての@RestControllerで発生する例外をキャッチし、エラーを返すことができます。
 */
@RestControllerAdvice
public class RestExceptionHandler {
  
  /*
   * @ExceptionHandlerアノテーションを使用して、Exceptionクラスの例外をキャッチしています。
   * Exceptionクラスは、Javaで定義されている最上位の例外クラスであり、どのような例外でもキャッチすることができます。
   */
  @ExceptionHandler
  private ResponseEntity<String> onError( Exception ex ) {

    final Logger log = LoggerFactory.getLogger( SampleRestApiController.class );


    log.error( ex.getMessage(), ex );

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String json = JsonMapper.map()
        .put( "message", "API エラー" )
        .put( "detail", ex.getMessage() )
        .put( "status", status.value() )
        .stringify();

    /*
     * ResponseEntityは、HTTPステータスコードやヘッダーなどの情報を含むレスポンスを表すクラスであり、レスポンスの本体部分はジェネリック型で指定することができます。
     * この例では、文字列型のJSON形式のデータを指定しています。
     */
        return new ResponseEntity<String>( json, status );
  }
}
